CREATE TABLE teens (
    id SERIAL PRIMARY KEY,
    name text,
    gender text
);

INSERT INTO teens
VALUES
    ('1', 'Коля', 'муж.'),
    ('2', 'Вася', 'муж.'),
    ('3', 'Ваня', 'муж.'),
    ('4', 'Миша', 'муж.'),
    ('5', 'Лёня', 'муж.'),
    ('6', 'Аня', 'жен.'),
    ('7', 'Оля', 'жен.'),
    ('8', 'Таня', 'жен.'),
    ('9', 'Света', 'жен.'),
    ('10', 'Маша', 'жен.'),
    ('11', 'Ира', 'жен.');
    
SELECT
    (t1.name || ', ' || t2.name) Пара
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender <> t2.gender AND t1.gender LIKE 'м%';
