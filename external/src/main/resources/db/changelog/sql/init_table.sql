create table case_id_latest(
    id integer not null,
    label varchar(10) not null,
    value int not null,
    PRIMARY KEY (id)
);

insert into case_id_latest(id, label, value) values(1, 'GSSDTTP', 0);