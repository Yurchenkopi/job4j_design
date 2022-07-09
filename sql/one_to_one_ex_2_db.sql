create table driversLicense(
    id serial primary key,
    name varchar(255)
);

create table person(
    id serial primary key,
    name varchar(255)
);

create table driversLicence_person(
    id serial primary key,
    driversLicense_id int references driversLicense(id) unique,
    person_id int references person(id) unique
);
