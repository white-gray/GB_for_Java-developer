create table products
(
    id         bigserial primary key,
    name      varchar(255),
    age      numeric(2, 0) not null
);

insert into products (name, age)
values ('����', 42),
       ('�����', 42),
       ('�������', 36),
       ('�����', 42),
       ('�����', 35),
       ('������', 33);
