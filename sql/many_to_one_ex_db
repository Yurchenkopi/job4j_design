create table engine(
    id serial primary key,
    name varchar(255)
);

create table carModel(
    id serial primary key,
    name varchar(255),
    engine_id int references engine(id)
);

insert into engine(name) values ('3ZR-FAE');
insert into carmodel(name, engine_id) values ('Toyota', 1);

select * from carModel;
select * from engine where id in (select id from carModel);