create table role(
	id serial primary key,
	name text
);
create table rules(
	id serial primary key,
	name text
);
create table levelAccept(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table users(
	id serial primary key,
	name text,
	levelAccept_id int references levelAccept(id)
);
create table state(
	id serial primary key,
	state text
);
create table category(
	id serial primary key,
	category text
);
create table item(
	id serial primary key,
	name text,
	users_id int references users(id),
	state_id int references state(id)
);
create table comments(
	id serial primary key,
	comment text,
	item_id int references item(id)
);
create table attache(
	id serial primary key,
	file bytea,
	item_id int references item(id)
);
