CREATE TABLE customers(
                          id serial primary key,
                          first_name text,
                          last_name text,
                          age int,
                          country text
);

CREATE TABLE orders(
                       id serial primary key,
                       amount int,
                       customer_id int references customers(id)
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES
       ('Иван', 'Иванов', 18, 'Россия'),
       ('Лена', 'Ленина', 20, 'Россия'),
       ('Максим', 'Максимов', 15, 'Россия'),
       ('Соня', 'Сонина', 30, 'США'),
       ('Катя', 'Катина', 15, 'Германия'),
       ('Федя', 'Федин', 40, 'США');

INSERT INTO orders (amount, customer_id)
VALUES
       (100, 1),
       (150, 1),
       (1000, 3),
       (200, 3),
       (255, 4),
       (235, 5),
       (555, 4);
-- 1. Выполните запрос, который вернет список клиентов, возраст которых является минимальным.
SELECT *
FROM customers
WHERE age = (
    SELECT MIN(age)
    FROM customers
    );

-- 2.Необходимо выполнить запрос, который вернет список пользователей,
-- которые еще не выполнили ни одного заказа.
-- вариант 1
SELECT first_name, last_name, age, country
FROM customers
LEFT OUTER JOIN orders o on customers.id = o.customer_id
WHERE o.id IS NULL
ORDER BY first_name, last_name, age, country ASC;

-- вариант 2
SELECT first_name, last_name, age, country
FROM customers
WHERE (first_name, last_name, age, country) NOT IN
      (
          SELECT first_name, last_name, age, country
          FROM orders o
                   JOIN customers c on o.customer_id = c.id
          GROUP BY first_name, last_name, age, country
          )
ORDER BY first_name, last_name, age, country ASC;