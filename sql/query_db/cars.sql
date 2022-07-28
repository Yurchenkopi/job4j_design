CREATE TABLE car_bodies (
    id SERIAL PRIMARY KEY,
    name varchar(100)
);

CREATE TABLE car_engines (
    id SERIAL PRIMARY KEY,
    name varchar(100)
);


CREATE TABLE car_transmissions (
    id SERIAL PRIMARY KEY,
    name varchar(100)
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name varchar(100),
    body_id int REFERENCES car_bodies(id),
    engine_id int REFERENCES car_engines(id),
    transmission_id int REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies (
VALUES
    ('1', 'Седан'),
    ('2', 'Хэтчбэк'),
    ('3', 'Универсал'),
    ('4', 'Лифтбэк'),
    ('5', 'Кабриолет'),
    ('6', 'Минивен'),
    ('7', 'Кроссовер'),
    ('8', 'Пикап'),
    ('9', 'Кузов 1'),
    ('10', 'Кузов 2')    
);

INSERT INTO car_engines (
VALUES
    ('1', 'K4M'),
    ('2', 'K7M'),
    ('3', '3ZR-FE'),
    ('4', 'CZDA'),
    ('5', 'CDCA'),
    ('6', '21129'),
    ('7', '21083'),
    ('8', 'Двигатель 1'),
    ('9', 'Двигатель 2'),
    ('10', 'Двигатель 3')
);

INSERT INTO car_transmissions (
VALUES
    ('1', 'DSG'),
    ('2', 'Вариатор'),
    ('3', 'Автомат'),
    ('4', 'МКПП'),
    ('5', 'КПП 1'),
    ('6', 'КПП 2'),
    ('7', 'КПП 3')
);

INSERT INTO cars (
VALUES
    ('1', 'Renault Logan', '1', '1', '4'),
    ('2', 'Renault Sandero', '2', '2', '3'),
    ('3', 'Lada Largus', '3', '1', '4'),
    ('4', 'Toyota RAV4', '7', '3', '3'),
    ('5', 'Skoda Octavia', '4', '4', '1'),
    ('6', 'VW Amarok', '8', '5', '3'),
    ('7', 'VAZ21083', '2', '7', '4'),
    ('8', 'Lada Priora', '1', null, '4'),
    ('9', 'Nissan Almera', '1', null, null),
    ('10', 'Subaru Impreza', null, null, '4')
);

--1. Вывод списка авто с обозначением кузова, двигателя, КПП
SELECT
    c.id,
    c.name "Название модели",
    cb.name "Тип кузова",
    ce.name "Обозначение двигателя",
    ct.name "Тип КПП"
FROM cars c
LEFT JOIN car_bodies cb ON c.body_id = cb.id
LEFT JOIN car_engines ce ON c.engine_id = ce.id
LEFT JOIN car_transmissions ct ON c.transmission_id = ct.id;

--2. Вывод списка кузовов, типы которых не исользуются в записях cars
SELECT
    cb.id,
    cb.name "Тип кузова"
FROM car_bodies cb
LEFT JOIN cars c ON c.body_id = cb.id
WHERE c.name ISNULL
ORDER BY cb.id ASC;

--3. Вывод списка двигателей, которые не встречаются в записях cars
SELECT
    ce.id,
    ce.name "Обозначение двигателя"
FROM car_engines ce
LEFT JOIN cars c ON c.engine_id = ce.id
WHERE c.name ISNULL
ORDER BY ce.id ASC;

--4. Вывод списка КПП, которые не встречаются в записях cars
SELECT
    ct.id,
    ct.name "Тип КПП"
FROM car_transmissions ct
LEFT JOIN cars c ON c.transmission_id = ct.id
WHERE c.name ISNULL
ORDER BY ct.id ASC;