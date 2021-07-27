create table brend(
    id serial primary key,
    brend text
);

create table auto(
    id serial primary key,
    model varchar(255),
    date date,
    brend_id int references brend(id)
);

insert into brend(brend) values ('VolksWagen');
insert into brend(brend) values ('LADA');
insert into brend(brend) values ('HONDA');

insert into auto(model, date, brend_id) values ('Polo', '01.01.2015' , 1);
insert into auto(model, date, brend_id) values ('Largus', '12.12.2019', 2);
insert into auto(model, date, brend_id) values ('Civic', '10.22.2009', 3);
insert into auto(model, date) values ('Granta', '01.06.2017');

select pp.model as Имя,pp.date as Дата, p.brend as Марка from auto as pp join brend as p on pp.brend_id = p.id;
select pp.model as Имя,pp.date as Дата, p.brend as Марка from auto as pp join brend as p on pp.brend_id = p.id where pp.date > '01.01.2012';
select pp.model as Имя,pp.date as Дата, p.brend as Марка from auto as pp join brend as p on pp.brend_id = p.id where pp.brend_id != 2;