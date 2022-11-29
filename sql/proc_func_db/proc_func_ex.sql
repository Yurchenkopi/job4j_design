create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    language 'plpgsql'
as $$
BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
END
$$;

call insert_data('product_2', 'producer_2', 15, 32);

create or replace procedure update_data(u_count integer, tax float, u_id integer)
    language 'plpgsql'
as $$
BEGIN
    if u_count > 0 THEN
        update products set count = count - u_count where id = u_id;
    end if;
    if tax > 0 THEN
        update products set price = price + price * tax;
    end if;
END;
$$;

call update_data(10, 0, 1);
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);
call update_data(0, 0.2, 0);

alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;

drop procedure update_data(u_count integer, tax float, u_id integer);

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    returns void
    language 'plpgsql'
as
$$
begin
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);

create or replace function f_update_data(u_count integer, tax float, u_id integer)
    returns integer
    language 'plpgsql'
as
$$
declare
    result integer;
begin
    if u_count > 0 THEN
        update products set count = count - u_count where id = u_id;
        select into result count from products where id = u_id;
    end if;
    if tax > 0 THEN
        update products set price = price + price * tax;
        select into result sum(price) from products;
    end if;
    return result;
end;
$$;

select f_update_data(10, 0, 1);

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 88, 115);

select f_update_data(0, 0.2, 0);

--Процедура, удаляющая конкретные данные по id из таблицы
--или сразу все данные в таблице, в случае, когда количество достигает предельного значения
create or replace procedure erase_data(limit_count integer, u_id integer)
    language 'plpgsql'
as $$
BEGIN
    if limit_count > 0 AND limit_count < (SELECT sum(count) FROM products) THEN
        DELETE FROM products;
    elseif u_id <> 0 THEN
        DELETE FROM products
        WHERE id = u_id;
    end if;
END;
$$;

--удаление из таблицы продукта с id=25
CALL erase_data(0, 25);

--удаление из таблицы продукта с id=25 или всех данных из неё, если общее количество продуктов больше 100
CALL erase_data(100, 25);

--Функция, удаляющая конкретные данные по id из таблицы и возвращающая общую стоимость оставшихся продуктов
--или сразу все данные в таблице, в случае, когда количество достигает предельного значения, и возвращающая
--количество строк в таблице
create or replace function f_erase_data(limit_count integer, u_id integer)
    returns integer
    language 'plpgsql'
as
$$
declare
    result integer;
begin
    if limit_count > 0 AND limit_count < (SELECT sum(count) FROM products) THEN
        DELETE FROM products;
        SELECT INTO result COUNT(*)
        FROM products;
    elseif u_id <> 0 THEN
        DELETE FROM products
        WHERE id = u_id;
        SELECT INTO result SUM(price)
        FROM products;
    end if;
    return result;
end;
$$;

--удалить продукт с id=29 и вернуть общую стоимость
SELECT f_erase_data(0, 29);

--очистить таблицу, если общее количество продуктов больше 105, и вернуть ноль
SELECT f_erase_data(105, 4);