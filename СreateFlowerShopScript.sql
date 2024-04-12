create table Season(
    season_id serial,
    season_name text not null
);

alter table Season
add constraint season_pk primary key (season_id);

create table Provider(
    provider_id serial,
    provider_name text not null,
    provider_address text null,
    provider_phone text null
);

alter table Provider
add constraint provider_pk primary key (provider_id);

create table Flower(
    flower_id serial,
    name text not null,
    cost double precision not null,
    height int not null,
    description text null,
    period_in_water int not null,
    season_id_fk int not null,
    provider_id_fk int not null
);

alter table Flower
add constraint flower_pk primary key (flower_id);

alter table Flower
add constraint season_fk foreign key (season_id_fk) references Season(season_id);

alter table Flower
add constraint provider_fk foreign key (provider_id_fk) references Provider(provider_id);

create table Additive(
    additive_id serial,
    additive_name text not null
);

alter table Additive
add constraint additive_pk primary key (additive_id);

create table Additive_Flower(
    additive_id_fk int not null,
    flower_id_fk int not null
);

alter table Additive_Flower
add constraint additive_flower_pk primary key (additive_id_fk, flower_id_fk);

create table Bouquet(
    bouquet_id serial,
    bouquet_name text not null,
    number_of_flowers int not null,
    cost double precision not null,
    flower_id_fk int not null
);

alter table Bouquet
add constraint bouquet_pk primary key (bouquet_id);

alter table Bouquet
add constraint flower_fk foreign key (flower_id_fk) references Flower(flower_id);

alter table Additive_Flower
add constraint flower_fk_add foreign key (flower_id_fk) references Flower(flower_id);

alter table Additive_Flower
add constraint additive_fk foreign key (additive_id_fk) references Additive(additive_id);