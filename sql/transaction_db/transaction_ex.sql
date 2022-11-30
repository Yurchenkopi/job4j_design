-- создаем таблицу в базе данных
create table products (
                          id serial primary key,
                          name varchar(50),
                          producer varchar(50),
                          count integer default 0,
                          price integer
);

-- добавляем тестовые строки
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('prod_10', 'manuf_10', 4, 777);
insert into products (name, producer, count, price) VALUES ('prod_11', 'manuf_11', 5, 7);
insert into products (name, producer, count, price) VALUES ('prod_12', 'manuf_12', 7, 77);
insert into products (name, producer, count, price) VALUES ('prod_15', 'manuf_15', 4, 22);
insert into products (name, producer, count, price) VALUES ('prod_17', 'manuf_17', 88, 888);

---------------------------
--пример isolation level committed
---------------------------

-- 1. Начинаем транзакцию номер 1, начинаем транзацию номер 2
BEGIN transaction;
-- 2. Обновляем значение цены у product_2
update products set price = 555 where name = 'product_2';
-- 3. Проверяем таблицу products и убеждаемся, что в первой транзакции цена изменилась
-- а во второй транзакции - таблица осталась исходной.
SELECT * FROM products;
-- 4. Делаем commit в транзакции номер 1 и убеждаемся, что транзакция номер 2 видит обновленную таблицу
COMMIT transaction;
SELECT * FROM products;

---------------------------
--пример isolation level repeatable read
---------------------------

-- 1. Начинаем транзакцию номер 1, начинаем транзацию номер 2
BEGIN transaction isolation level repeatable read;
-- 2. Удаляем продукт с ценой 555 в транзакции 1
delete from products where price = 555;
-- 3. Пробуем удалить продукт с ценой 555 из транзации 2 и убеждаемся, что операция не будет выолнена
--    до момента отката или завершения транзакции 1
delete from products where price = 555;
-- 4. Делаем commit в транзакции 1 и убеждаемся, что в транзакции 2 вылетает ошибка о невозможности
--    параллельного удаления
commit;

---------------------------
--пример isolation level serializable
---------------------------

-- 1. Начинаем транзакцию номер 1, начинаем транзацию номер 2
begin transaction isolation level serializable;
-- 2. Обновляем цену у продукта "prod_17" в транзакции 2
update products set price = 400 where name = 'prod_17';
-- 3. Удаляем продукт "prod_11" и находим среднюю цену всех продуктов в транзакции 1
delete from products where name = 'prod_11';
select ROUND(AVG(count * price), 2) from products;
-- 4. Коммитим изменения транзакции 2, затем транзакции 1 и убеждаемся в работе уровня
-- изолированности serializable по сообщениям возникающих ошибок
commit;

---------------------------
--пример работы с точками сохранения
---------------------------

-- 1. Начинаем транзакцию
begin transaction;
-- 2. Добавляем данные в таблицу и создаем первую точку восстановления
insert into products (name, producer, count, price) VALUES ('product_20', 'producer_20', 1, 100);
savepoint first;
-- 3. Добавляем данные в таблицу и создаем вторую точку восстановления
insert into products (name, producer, count, price) VALUES ('product_21', 'producer_21', 33, 500),
                                                           ('product_22', 'producer_22', 11, 400);
savepoint second;
-- 4. Увеличиваем количество в три раза для стоимости <=100 и создаем третью точку восстановления
update products set count = count * 3 where price <= 100;
savepoint third;
-- 5. Проверяем итоговую таблицу, удаляем позиции со стоимостью больше 300, откатываемся сначала к третьей, а потом
-- ко второй точке восстановления, проверяя таблицу. Убеждаемся, что данные восстановлены корректно
select * from products;
delete from products where price > 300;
select * from products;
rollback to third;
select * from products;
rollback to second;
select * from products;
-- 6. Убеждаемся, что третья точка восстановления больше недоступна.
rollback to third;
-- 7. Откатываемся к первой точке и завершаем транзакцию
rollback to first;
commit;

---------------------------
-- Работа с курсорами
---------------------------

-- удаление предыдущих записей
delete FROM products;

-- заполнение таблицы данными
insert into products (name, count, price) VALUES ('product_1', 1, 5);
insert into products (name, count, price) VALUES ('product_2', 2, 10);
insert into products (name, count, price) VALUES ('product_3', 3, 15);
insert into products (name, count, price) VALUES ('product_4', 4, 20);
insert into products (name, count, price) VALUES ('product_5', 5, 25);
insert into products (name, count, price) VALUES ('product_6', 6, 30);
insert into products (name, count, price) VALUES ('product_7', 7, 35);
insert into products (name, count, price) VALUES ('product_8', 8, 40);
insert into products (name, count, price) VALUES ('product_9', 9, 45);
insert into products (name, count, price) VALUES ('product_10', 10, 50);
insert into products (name, count, price) VALUES ('product_11', 11, 55);
insert into products (name, count, price) VALUES ('product_12', 12, 60);
insert into products (name, count, price) VALUES ('product_13', 13, 65);
insert into products (name, count, price) VALUES ('product_14', 14, 70);
insert into products (name, count, price) VALUES ('product_15', 15, 75);
insert into products (name, count, price) VALUES ('product_16', 16, 80);
insert into products (name, count, price) VALUES ('product_17', 17, 85);
insert into products (name, count, price) VALUES ('product_18', 18, 90);
insert into products (name, count, price) VALUES ('product_19', 19, 95);
insert into products (name, count, price) VALUES ('product_20', 20, 100);

-- начинаем транзакцию и создаем курсор
BEGIN;
DECLARE
    cursor_products cursor for
    select * from products;

-- извлекаем сразу 10 записей
FETCH 10 FROM cursor_products;
-- ещё одну
FETCH FROM cursor_products;
-- ещё одну
FETCH FROM cursor_products;
-- перемещаем курсор на 2 записи вперёд
MOVE FORWARD 2 FROM cursor_products;
-- извлекаем следующую запись
FETCH NEXT FROM cursor_products;
-- закрываем курсор и завершаем транзакцию
CLOSE cursor_products;
commit;

---------------------------
--пример обратного прохода по записям таблицы
---------------------------

-- начинаем транзакцию и создаем курсор
BEGIN;
DECLARE
    cursor_products SCROLL cursor for
    select * from products;

-- переход на последнюю запись
FETCH LAST FROM cursor_products;

-- переход на 15ю запись
MOVE BACKWARD 6 FROM cursor_products;
-- извлекаем запись
FETCH FROM cursor_products;

-- переход на 7ю запись
MOVE BACKWARD 9 FROM cursor_products;
-- извлекаем запись
FETCH FROM cursor_products;

-- переход на 2ю запись
MOVE BACKWARD 6 FROM cursor_products;
-- извлекаем запись
FETCH FROM cursor_products;

-- извлекаем 1ю запись
FETCH BACKWARD FROM cursor_products;

-- закрываем курсор и завершаем транзакцию
CLOSE cursor_products;
COMMIT;
