-- создаем базу данных
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