create table serialNum(
	id serial primary key,
	serialNumber int,
	partionNumber int
);
create table pc(
	id serial primary key,
	activ boolean,
	name text,
	price integer,
	count integer,
	serialNumber_id int references serialNum(id) unique
);
create table buyer(
	id serial primary key,
	name text,
	contact varchar(255)
);
create table orders(
	id serial primary key,
	pc_id int references pc(id),
	buyer_id int references buyer(id)
);