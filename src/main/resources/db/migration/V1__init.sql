create table products
(
    id         bigserial primary key,
    name      varchar(255),
    age      numeric(2, 0) not null
);

insert into products (name, age)
values ('Вано', 42),
       ('Диман', 42),
       ('Клавдия', 36),
       ('Серый', 42),
       ('Алекс', 35),
       ('Олежка', 33);
