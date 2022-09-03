--Примеры из урока
create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

select s.name, a.name, count(a.name) from students as s
    join orders o on s.id = o.student_id
    join books b on o.book_id = b.id
    join authors a on b.author_id = a.id
    group by (s.name, a.name)
    having count(a.name) >= 2;
    
create view show_students_with_2_or_more_books
    as select s.name as student, count(a.name), a.name as author from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
         group by (s.name, a.name) having count(a.name) >= 2;
         
select * from show_students_with_2_or_more_books;
--------------------------------------------------
--Изменение таблиц из примеров из урока
--------------------------------------------------
--Добавление столбца "стоимость" к таблице "books"
ALTER TABLE books
ADD COLUMN price numeric(10,4);

--Добавление столбца "дата рождения" к таблице "authors"
ALTER TABLE authors
ADD COLUMN birthday date;

--Переименовывание студентов в книголюбов
ALTER TABLE students
RENAME TO bibliophiles;

--Переименовывание столбца student_id в customer_id
ALTER TABLE orders
RENAME COLUMN student_id TO customer_id;

--Обновление добавленных прежде данных
UPDATE authors a SET birthday = '1809-03-20' WHERE a.name LIKE '%Гоголь';
UPDATE authors a SET birthday = '1799-06-06' WHERE a.name LIKE '%Пушкин';
UPDATE books b SET price = 400 WHERE b.name LIKE '%Капитанская%';
UPDATE books b SET price = 555 WHERE b.name LIKE '%Онегин%';
UPDATE books b SET price = 444 WHERE b.name LIKE '%Дубровский%';
UPDATE books b SET price = 600 WHERE b.name LIKE '%души%';
UPDATE books b SET price = 100 WHERE b.name LIKE '%Вий%';

--Заполение таблиц новыми данными
INSERT INTO authors (name, birthday) VALUES ('Борис Акунин', '1956-05-20');
INSERT INTO authors (name, birthday) VALUES ('Кир Булычёв', '1934-10-18');
INSERT INTO authors (name, birthday) VALUES ('Януш Вишневский', '1954-08-18');

INSERT INTO books (name, author_id, price) VALUES ('Алмазная колесница', 3, 1231);
INSERT INTO books (name, author_id, price) VALUES ('Аристономия', 3, 1200);
INSERT INTO books (name, author_id, price) VALUES ('Турецкий гамбит', 3, 800);
INSERT INTO books (name, author_id, price) VALUES ('Одиночество в сети', 4, 777);
INSERT INTO books (name, author_id, price) VALUES ('Посёлок', 5, 900);

INSERT INTO bibliophiles (name) VALUES ('Вася Пупкин');
INSERT INTO bibliophiles (name) VALUES ('Аня Федина');

INSERT INTO orders (book_id, customer_id) VALUES (7, 3);
INSERT INTO orders (book_id, customer_id) VALUES (7, 1);
INSERT INTO orders (book_id, customer_id) VALUES (8, 2);
INSERT INTO orders (book_id, customer_id) VALUES (9, 4);
INSERT INTO orders (book_id, customer_id) VALUES (10, 4);
INSERT INTO orders (book_id, customer_id) VALUES (11, 1);
INSERT INTO orders (book_id, customer_id) VALUES (11, 2);
INSERT INTO orders (book_id, customer_id) VALUES (11, 3);
INSERT INTO orders (book_id, customer_id) VALUES (11, 4);

--Общая выборка по покупателям, книгам и авторам
SELECT bib.name Покупатель,
       a.name Автор, 
       a.birthday "Дата рождения",
       b.name "Название книги",
       b.price Стоимость
FROM bibliophiles bib
JOIN orders o ON o.customer_id = bib.id
JOIN books b on o.book_id = b.id
JOIN authors a ON a.id = b.author_id;

--Запрос нахождения книголюбов, купившего книги авторов с датой рождения после 1950-01-01
--Сначала создается представление, затем делается выборка
CREATE VIEW authors_from_1950
  AS SELECT bib.name Покупатель,
            a.name Автор,
            b.name Название,
            b.price Стоимость
     FROM bibliophiles bib
     JOIN orders o ON o.customer_id = bib.id
     JOIN books b on o.book_id = b.id
     JOIN authors a ON a.id = b.author_id
     WHERE a.birthday >= '1950-01-01';
     
SELECT Покупатель, Автор, Название, Стоимость
FROM authors_from_1950;

--Запрос нахождения книголюбов, купившего книги авторов с датой рождения после 1700-01-01,
--но до 1900-01-01
--Сначала создается представление, затем делается выборка
CREATE VIEW authors_from_1700_till_1900
  AS SELECT bib.name Покупатель,
            a.name Автор,
            b.name Название,
            b.price Стоимость
     FROM bibliophiles bib
     JOIN orders o ON o.customer_id = bib.id
     JOIN books b on o.book_id = b.id
     JOIN authors a ON a.id = b.author_id
     WHERE a.birthday >= '1700-01-01' AND a.birthday <= '1900-01-01';
     
SELECT Покупатель, Автор, Название, Стоимость
FROM authors_from_1700_till_1900;

--Запрос нахождения книголюбов, купивших авторов с датой рождения после 1950-01-01 
--и при этом потративших на все книги этих авторов больше 2000 у.е.
SELECT Покупатель,
       SUM(Стоимость) Итого
FROM authors_from_1950
GROUP BY Покупатель
HAVING SUM(Стоимость) > 2000;

--Запрос нахождения книголюбов, купивших авторов с датой рождения после 1700-01-01, но до 1900-01-01 
--и при этом потративших на все книги этих авторов больше 500 у.е.
SELECT Покупатель,
       SUM(Стоимость) Итого
FROM authors_from_1700_till_1900
GROUP BY Покупатель
HAVING SUM(Стоимость) >= 500;

--Удаление созданных представлений
DROP VIEW authors_from_1950;
DROP VIEW authors_from_1700_till_1900;