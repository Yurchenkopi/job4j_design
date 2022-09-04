create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--триггер, работающий на ROW уровне
create or replace function discount_row_func()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger_after_row
    after insert
    on products
    for each row
    execute procedure discount_row_func();

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 60);

SELECT * FROM products;

ALTER TABLE products
DISABLE TRIGGER discount_trigger_after_row;

drop trigger discount_trigger_after_row on products;

--Триггер, работающий на STATEMENT уровне
create or replace function discount_statement_func()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger_after_statement
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure discount_statement_func();
    
INSERT INTO products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 3, 60);

SELECT * FROM products;

alter table products disable trigger discount_trigger_after_statement;

drop trigger discount_trigger_after_statement on products;

--------------------------------------------
--ЗАДАНИЕ
--------------------------------------------
--1. Создание триггера, работающего на STATEMENT уровне ПОСЛЕ вставки любой позиции и добавляющего 
--в стоимость ЭТОЙ позиции налог 20%
CREATE OR REPLACE FUNCTION tax_statement_func()
    RETURNS TRIGGER AS
$$
    BEGIN
        UPDATE products
        SET price = price * 1.2
        WHERE id = (SELECT id FROM inserted);
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger_after_statement
    AFTER INSERT ON products
    REFERENCING NEW TABLE AS inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE tax_statement_func();
    
--Вставка тестовых данных, выборка, деактивация триггера и его последующее удаление
INSERT INTO products (name, producer, count, price)
VALUES ('product_7', 'producer_7', 7, 100);

SELECT * FROM products;

ALTER TABLE products
DISABLE TRIGGER tax_trigger_after_statement;

DROP TRIGGER tax_trigger_after_statement on products;

--2. Создание триггера, работающего на ROW уровне ДО вставки любой позиции в таблицу и добавляющего 
--в стоимость ЭТОЙ позиции налог 20%
CREATE OR REPLACE FUNCTION tax_row_func()
    RETURNS TRIGGER AS
$$
    BEGIN
        NEW.price = NEW.price * 1.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger_before_row
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE tax_row_func();
    
--Вставка тестовых данных, выборка, деактивация триггера и его последующее удаление
INSERT INTO products (name, producer, count, price)
VALUES ('product_10', 'producer_10', 4, 100);

SELECT * FROM products;

ALTER TABLE products
DISABLE TRIGGER tax_trigger_before_row;

DROP TRIGGER tax_trigger_before_row on products;

--3. Нужно написать триггер на row уровне, который при вставке продукта в таблицу products,
--будет заносить имя, цену и текущую дату в таблицу history_of_price
CREATE TABLE history_of_price (
    id serial PRIMARY KEY,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE OR REPLACE FUNCTION add_product_to_hof_func()
    RETURNS TRIGGER AS
$$
    BEGIN
        INSERT INTO history_of_price(name, price, date)
        VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_product_to_hof
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE add_product_to_hof_func();
    
--Вставка тестовых данных, выборка, деактивация триггера и его последующее удаление    
INSERT INTO products (name, producer, count, price)
VALUES ('product_15', 'producer_15', 10, 100);

SELECT * FROM products;
SELECT * FROM history_of_price;

ALTER TABLE products
DISABLE TRIGGER add_product_to_hof;

DROP TRIGGER add_product_to_hof on products;