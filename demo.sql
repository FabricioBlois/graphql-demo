-- Tables
create table brand
(
    id   int not null primary key,
    name text
);

create table beer
(
    id       int not null primary key,
    brand_id int not null
        constraint fk_beer_brand references brand,
    type     text
);

create table message
(
    id      int not null primary key,
    beer_id int not null
        constraint fk_message_beer references beer,
    value   text
);

-- Init insert
insert into brand (id, name)
values (1, 'Brewdog'),
       (2, 'Paulaner');

insert into beer (id, brand_id, type)
values (1, 1, 'IPA'),
       (2, 2, 'Weizenbier');

insert into message (id, beer_id, value)
values (1, 1, 'Nice beer'),
       (2, 2, 'Good beer'),
       (3, 1, 'Bitter beer'),
       (4, 2, 'Heavy beer');
