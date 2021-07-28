create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
	people_id int references people(id),
    device_id int references devices(id)
);

insert into devices (name, price) values ('Весы', 4500);
insert into devices (name, price) values ('Телевизор', 12000);
insert into devices (name, price) values ('Часы', 6000);
insert into devices (name, price) values ('Холодильник', 22000);
insert into devices (name, price) values ('Смартфон', 17000);
insert into devices (name, price) values ('Финтнес браслет', 2500);
insert into devices (name, price) values ('Весы', 4500);
insert into people (name) values ('Иван');
insert into people (name) values ('Федр');
insert into people (name) values ('Петр');
insert into people (name) values ('Жанна');
insert into people (name) values ('Анжела');
insert into devices_people (people_id, device_id) values (1, 1);
insert into devices_people (people_id, device_id) values (1, 2);
insert into devices_people (people_id, device_id) values (1, 3);
insert into devices_people (people_id, device_id) values (1, 4);
insert into devices_people (people_id, device_id) values (1, 5);
insert into devices_people (people_id, device_id) values (2, 2);
insert into devices_people (people_id, device_id) values (2, 6);
insert into devices_people (people_id, device_id) values (2, 7);
insert into devices_people (people_id, device_id) values (3, 5);
insert into devices_people (people_id, device_id) values (3, 7);
insert into devices_people (people_id, device_id) values (4, 1);
insert into devices_people (people_id, device_id) values (4, 6);
insert into devices_people (people_id, device_id) values (5, 4);
insert into devices_people (people_id, device_id) values (5, 5);

select avg(price) from devices;

select s.name, avg(sss.price) from people as s 
join devices_people ss on ss.people_id = s.id
join devices sss on ss.device_id = sss.id
group by s.name;

select s.name, avg(sss.price) from people as s 
join devices_people ss on ss.people_id = s.id
join devices sss on ss.device_id = sss.id
group by s.name
having avg(sss.price) > 5000;