create table emploees (
	id serial primary key,
	name text
);

create table departments (
	id serial primary key,
	name text,
	emploees_id int references emploees(id)
);

insert into emploees (name) values ('Иван'), ('Федр'), ('Сергей'),('Жанна'),('Лариса');
insert into departments (name, emploees_id) values ('Бухгалтерия', 4), ('Секретарь', 5),
 ('Администратор', 1), ('Разработка', null), ('Техник', 2), ('Электрик', 3), ('Бариста', null);

select * from emploees e left join departments d on d.emploees_id = e.id;
select * from emploees e right join departments d on d.emploees_id = e.id;
select * from emploees e full join departments d on d.emploees_id = e.id;
select * from emploees cross join departments;

select * from departments d left join emploees e on d.emploees_id = e.id where e.name isnull;

select * from departments d right join emploees e on d.emploees_id = e.id;
select * from emploees e left join departments d on d.emploees_id = e.id;

create table teens(
	id serial primary key,
	name text,
	gender text
);

insert into teens(name, gender) values ('Иван','Мужской'), ('Федр','Мужской'), ('Жанна','Женский'),
('Сергей','Мужской'), ('Светлана','Женский'),('Алла','женский');

select * from teens d cross join teens t where d.gender != t.gender;