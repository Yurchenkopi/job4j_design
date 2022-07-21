create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices
VALUES 
    ('1', 'Смартфон', '5000.25'),
    ('2', 'Смартбраслет', '1000.25'),
    ('3', 'Колонка', '2000.45'),
    ('4', 'Плеер', '500.32'),
    ('5', 'Проектор', '4000'),
    ('6', 'Беспроводная зарядка', '2550'),
    ('7', 'Наушники', '3050'),
    ('8', 'Смартрозетка', '850'),
    ('9', 'Комлект "Умный дом"', '7000.32'),
    ('10', 'Ноутбук', '130000');
    
INSERT INTO people
VALUES 
    ('1', 'Иван Иванов'),
    ('2', 'Петр Сидоров'),
    ('3', 'Боря Петров'),
    ('4', 'Паша Клюев'),
    ('5', 'Максим Максимов'),
    ('6', 'Игорь Блинов'),
    ('7', 'Федя Пупкин'),
    ('8', 'Коля Непомнящий'),
    ('9', 'Лёня Незнайкин'),
    ('10', 'Аня Ножкина'),
    ('11', 'Оля Ручкина'),
    ('12', 'Маша Федина'),
    ('13', 'Катя Петрова'),
    ('14', 'Алина Губкина');
    
INSERT INTO devices_people
VALUES
    ('1', '2', '1'),
    ('2', '8', '1'),
    ('3', '3', '1'),
    ('4', '1', '2'),
    ('5', '7', '2'),
    ('6', '10', '2'),
    ('7', '4', '3'),
    ('8', '5', '3'),
    ('9', '8', '3'),
    ('10', '6', '4'),
    ('11', '7', '4'),
    ('12', '4', '4'),
    ('13', '9', '5'),
    ('14', '4', '5'),
    ('15', '3', '5'),
    ('16', '1', '10'),
    ('17', '2', '10'),
    ('18', '10', '10'),
    ('19', '4', '11'),
    ('20', '7', '11'),
    ('21', '6', '11'),
    ('22', '1', '12'),
    ('23', '7', '12'),
    ('24', '10', '12'),
    ('25', '3', '13'),
    ('26', '8', '13'),
    ('27', '9', '13'),
    ('28', '9', '14'),
    ('29', '10', '14'),
    ('30', '6', '14');

-- Вывод информации о всех устройствах и пользователях
SELECT dp.id, p.name, d.name, d.price
FROM devices_people AS dp
JOIN people AS p ON dp.people_id = p.id
JOIN devices AS d ON dp.device_id = d.id;

--Средняя цена устройств
SELECT AVG(price) FROM devices;

-- Ср. цена устройств для каждого человека
SELECT p.name AS Имя, AVG(d.price) AS "Средний балл"
FROM devices_people AS dp
JOIN people AS p ON dp.people_id = p.id
JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name;

-- Вывод ср. цены устройсв для людей с условием "средняя больше 5000"
SELECT p.name AS Имя, AVG(d.price) AS "Средний балл"
FROM devices_people AS dp
JOIN people AS p ON dp.people_id = p.id
JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 5000;


