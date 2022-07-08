create table driversLicense(
    id serial primary key,
    name varchar(255)
);

create table person(
    id serial primary key,
    name varchar(255),
    driversLicense_id int references driversLicense(id) unique
);
