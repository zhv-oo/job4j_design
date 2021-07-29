create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type (name) values ('СЫР'), ('МОЛОКО'), ('ХЛЕБОБУЛОЧНЫЕ ИЗДЕЛИЯ'), ('НАПИТКИ'), ('КОЛБАСЫ');

insert into product(name, type_id, expired_date, price) values
('Плавленый сырок', 1, '12.01.2020', 25.5),
('Витязь', 1, '12.01.2021', 325.5),
('Российский', 1, '12.03.2020', 412.7),
('Буренка', 2, '03.02.2021', 45.8),
('Зеленый луг', 2, '04.14.2021', 42.3),
('Белый хлеб', 3, '07.23.2021', 22.2),
('Серый', 3, '07.22.2021', 27.6),
('Булка сдобная', 3, '07.28.2021', 16.9),
('Лаваш', 3, '07.29.2021', 12.4),
('Буратино', 4, '04.16.2021', 36.5),
('Сервелат', 5, '06.19.2021', 321.3),
('Докторская', 5, '07.03.2021', 182.9);

select s.name as Продукт
from product as s
join type ss on s.type_id = ss.id
where ss.name like 'СЫР';

select s.name as Продукт
from product as s
join type ss on s.type_id = ss.id
where s.name like 'Буратино';

select s.name as Продукт
from product as s
join type ss on s.type_id = ss.id
where s.expired_date < '01.01.2021';

select ss.name as Тип, s.name as Тип, max(s.price) as Цена
from product as s
join type ss on s.type_id = ss.id
group by s.name, ss.name, s.price
order by s.price desc
limit 1;

select s.name as Продукт
from product as s
join type ss on s.type_id = ss.id
where ss.name like 'СЫР' or ss.name like 'МОЛОКО';

select ss.name as Тип, count(s.id) as Количество
from product as s
join type ss on s.type_id = ss.id
group by ss.name;

select ss.name as Тип, count(s.id) as Количество
from product as s
join type ss on s.type_id = ss.id
group by ss.name
HAVING  count(s.id) >= 2;
