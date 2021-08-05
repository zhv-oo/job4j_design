create table body(
	id serial primary key,
	name text
);

create table engine(
	id serial primary key,
	name text
);

create table gear(
	id serial primary key,
	name text
);

create table car(
	id serial primary key,
	name text,
	body_id int references body(id),
	engine_id int references engine(id),
	gear_id int references gear(id)
);

insert into body (name) values ('Седан'), ('Купэ'), ('Хэчбек'), ('Универсал'), ('Паркетник');

insert into engine (name) values ('0,7'), ('1,0'), ('1,4'), ('1,5'), ('2,0');

insert into gear (name) values ('Механика'), ('Робот'), ('Автомат'), ('Вариатор');

insert into car (name, body_id, engine_id, gear_id) 
	   values ('Honda Accord', 1, 5, 3), ('Subaru Stella', 4, 1, 4),
	   ('Kia Rio', 3, 4, 1), ('Toyota Yaris', 3, 2, 3), 
	   ('Volkswagen Tiguan', 5, 3, 2), ('Toyota Gt-86', 2, 5, 1);

select c.name as Машина, b.name as Кузов, e.name as Двигатель, g.name as "Коробка передач"
from car as c
join body b on b.id = c.body_id
join engine e on e.id = c.engine_id
join gear g on g.id = c.gear_id
group by c.name, b.name, g.name, e.name;

insert into body (name) values ('Внедорожник');
insert into engine (name) values ('3,5');
insert into gear (name) values ('Селектроная');

select b.name as Кузов from body as b
left join car c on c.body_id = b.id
where c.body_id is null;

select e.name as Двигатель from engine as e
left join car c on c.engine_id = e.id
where c.engine_id is null;

select g.name as "Коробка передач" from gear as g
left join car c on c.gear_id = g.id
where c.gear_id is null;