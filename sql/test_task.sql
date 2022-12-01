CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

DELETE FROM company;
DELETE FROM person;

INSERT INTO company (id, name)
VALUES
       (1, 'ООО "Рога и копыта"'),
       (2, 'ООО "Электрон"'),
       (3, 'АО "СпецСтрой"'),
       (4, 'ФГПУ "НИИСПЕЦТЕХ"'),
       (5, 'ООО "Шинохран"'),
       (6, 'ООО "Пушкин"');

INSERT INTO person (id, name, company_id)
VALUES
       (1, 'Иванов Иван', 1),
       (2, 'Вася Петров', 1),
       (3, 'Федя Федин', 2),
       (4, 'Коля Колин', 2),
       (5, 'Маша Иванова', 2),
       (6, 'Оля Громова', 3),
       (7, 'Аня Костина', 3),
       (8, 'Соня Сонина', 3),
       (9, 'Рома Фокин', 4),
       (10, 'Евгений Онегин', 6),
       (11, 'Саша Гайкин', 5),
       (12, 'Света Колесова', 5);

-- 1.1. имена всех person, которые не состоят в компании с id = 5

SELECT id, name
FROM person
WHERE company_id <> 5;

-- 1.2. название компании для каждого человека
SELECT p.id, p.name, c.name
FROM person p
JOIN company c ON p.company_id = c.id;

-- 1.3. п.1.1 и 1.2 в одном запросе
SELECT p.id, p.name, c.name
FROM person p
JOIN company c ON p.company_id = c.id
WHERE company_id <> 5;

-- 2. выбрать название компании с максимальным количеством человек + количество человек в этой компании.
-- Нужно учесть, что таких компаний может быть несколько

SELECT cc.name, COUNT(*) Количество
FROM company cc
JOIN person pp ON pp.company_id = cc.id
GROUP BY cc.name
HAVING COUNT(*) = (
    SELECT MAX(q_in.num)
    FROM (
        SELECT p.company_id, COUNT(*) num
        FROM person p
        GROUP BY p.company_id
        ) q_in
    );