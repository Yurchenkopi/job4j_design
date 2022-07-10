create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna
VALUES
    ('1', 'clown fish', '3650', null),
    ('2', 'bear', '10950', null),
    ('3', 'shark', '10950', null),
    ('4', 'penguin Aptenodytes', '7300', '1778-01-01'),
    ('5', 'Giraffa camelopardalis', '9125', '1758-01-01'),
    ('6', 'sawfish Pristidae', '29200', '1838-01-01'),
    ('7', 'Loxodonta africana', '20900', '1797-01-01');

-- Извлечение данных, у которых имя name содержит подстроку fish

SELECT *
FROM fauna
WHERE name LIKE '%fish%';

-- Извлечение данных, у которых сред. продолжительность жизни находится в диапазоне 10 000 и 21 000

SELECT *
FROM fauna
WHERE avg_age >= 10000 AND avg_age <= 21000;

-- или через BETWEEN

SELECT *
FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

-- Извлечение данных, у которых дата открытия не известна (null)

SELECT *
FROM fauna
WHERE discovery_date IS null;

-- Извлечение данных видов, у которых дата открытия раньше 1950 года

SELECT *
FROM fauna
WHERE discovery_date <= '1950-01-01';