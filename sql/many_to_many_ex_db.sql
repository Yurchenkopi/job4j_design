create table drivers(
    id serial primary key,
    name varchar(255)
);

create table carModel(
    id serial primary key,
    name varchar(255)
);

create table carModel_drivers(
    id serial primary key,
    drivers_id int references drivers(id),
    carModel_id int references carModel(id)
);

insert into drivers(name) values ('Petya');
insert into drivers(name) values ('Vasya');
insert into drivers(name) values ('Alex');
insert into carModel(name) values ('VAZ');
insert into carModel(name) values ('UAZ');
insert into carModel(name) values ('ZAZ');

insert into carModel_drivers(drivers_id, carModel_id) VALUES (1, 1);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (1, 2);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (1, 3);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (2, 1);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (2, 2);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (2, 3);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (3, 1);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (3, 2);
insert into carModel_drivers(drivers_id, carModel_id) VALUES (3, 3);

select * from carModel_drivers;
select * from drivers where id in (select id from carModel_drivers);
select * from carModel where id in (select id from carModel_drivers);