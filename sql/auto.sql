create table auto(
    id serial primary key,
    model varchar (255),
    color varchar (255),
    regnum varchar (255),
    power float8,
    description text
);
insert into auto (model, color, regnum, power, description) values ('ВАЗ2101', 'желтый', 'О555ОО02', '67.7', 'классика');
select * from auto;
update auto set description='нестареющая классика';
delete from auto;
