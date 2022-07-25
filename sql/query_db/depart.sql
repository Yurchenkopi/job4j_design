CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name varchar(200)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name varchar(200),
    department_id int REFERENCES departments(id)
);

INSERT INTO departments
VALUES
    ('1', 'Металлообрабатывающий цех'),
    ('2', 'Бухгалтерия'),
    ('3', 'АйТи'),
    ('4', 'ОТК'),
    ('5', 'Юридический отдел'),
    ('6', 'Отдел закупок'),
    ('7', 'Отдел кадров'),
    ('8', 'КБ');

INSERT INTO employees
VALUES
    ('1', 'Васёк', '1'),
    ('2', 'Коля', '1'),
    ('3', 'Аня', '2'),
    ('4', 'Оля', null),
    ('5', 'Света', '2'),
    ('6', 'Соня', '5'),
    ('7', 'Иван', '3'),
    ('8', 'Боря', null),
    ('9', 'Саша', '3'),
    ('10', 'Вениамин', '6'),
    ('11', 'Костя', null);
    
SELECT * FROM employees;
SELECT * FROM departments;

--Inner Join
SELECT * FROM employees e
JOIN departments d ON e.department_id = d.id;
   
--Outer Left Join one to many
SELECT * FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

--Outer Left Join many to one
SELECT * FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

--Outer Right Join one to many
SELECT * FROM departments d
RIGHT JOIN employees e ON e.department_id = d.id;

--Outer Right Join many to one
SELECT * FROM employees e
RIGHT JOIN departments d ON d.id = e.department_id;

--Full Join
SELECT * FROM employees e
FULL JOIN departments d ON d.id = e.department_id;

--Cross Join
SELECT * FROM employees e
CROSS JOIN departments d;

--Запрос с Left Join для фильтрации работников, которые сокращены (без отдела)
SELECT * FROM employees e
LEFT JOIN departments d ON e.department_id = d.id
WHERE e.department_id is NULL;

--Запрос с Left Join для фильтрации отделов без работников
SELECT * FROM departments d
LEFT JOIN employees e ON e.department_id = d.id
WHERE e.id is NULL;

--Запросы с Left и Right Join с одинаковыми результатами вывода
SELECT
    e.id, 
    e.name "Имя работника", 
    d.name "Название отдела"
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

SELECT
    e.id, 
    e.name "Имя работника", 
    d.name "Название отдела"
FROM departments d
RIGHT JOIN employees e ON e.department_id = d.id;