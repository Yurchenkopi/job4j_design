CREATE TABLE currency (
    id serial PRIMARY KEY,
    name varchar (100)
);

CREATE TABLE manufacturer (
    id serial PRIMARY KEY,
    name varchar(255),
    country varchar(255)
);

CREATE TABLE product (
    id serial PRIMARY KEY,
    name text,
    price numeric(10,4),
    currency_id int REFERENCES currency(id),
    manufacturer_id int REFERENCES manufacturer(id)
);

INSERT INTO currency
VALUES
    ('1', 'RUB'),
    ('2', 'EURO'),
    ('3', 'USD'),
    ('4', 'CNY');

INSERT INTO manufacturer
VALUES
    ('1', 'ООО "Рога и копыта"', 'Россия'),
    ('2', 'ООО "СпецТехСтрой"', 'Россия'),
    ('3', 'Ikea', 'Sweden'),
    ('4', 'ABC ltd', 'China'),
    ('5', 'SpTechTool', 'USA');
    
INSERT INTO product
VALUES
    ('1', 'Ведро', '100', '1', '1'),
    ('2', 'Таз', '200.5', '1', '1'),
    ('3', 'Бочка', '1000.45', '1', '1'),
    ('4', 'Стеллаж', '2234.22', '1', '2'),
    ('5', 'Прожектор', '4212.76', '1', '2'),
    ('6', 'Лестница', '3423.2', '1', '2'),
    ('7', 'Billy', '5555.55', '2', '3'),
    ('8', 'Omhult', '543.80', '2', '3'),
    ('9', 'Besto', '34534.10', '2', '3'),
    ('10', 'Азбука', '43', '4', '4'),
    ('11', 'Кубики', '88', '4', '4'),
    ('12', 'Дрель', '175.2', '3', '5'),
    ('13', 'Перфоратор', '250.4', '3', '5'),
    ('14', 'Фрезер', '357.1', '3', '5');

SELECT pr.id, pr.name, man.name, price, cur.name,country
FROM product AS pr
INNER JOIN manufacturer AS man ON pr.manufacturer_id = man.id
INNER JOIN currency AS cur ON pr.currency_id = cur.id;

SELECT 
        pr.id, 
        pr.name AS "Название продукта", 
        man.name AS Производитель, 
        price AS Стоимость,
        cur.name AS Валюта,
        country AS Страна
FROM product AS pr
JOIN manufacturer AS man ON pr.manufacturer_id = man.id
JOIN currency AS cur ON pr.currency_id = cur.id;

SELECT 
        pr.id, 
        pr.name AS Продукт, 
        man.name AS Производитель, 
        price AS Стоимость,
        cur.name AS Валюта,
        country AS Страна
FROM product AS pr
JOIN manufacturer AS man ON pr.manufacturer_id = man.id
JOIN currency AS cur ON pr.currency_id = cur.id;
