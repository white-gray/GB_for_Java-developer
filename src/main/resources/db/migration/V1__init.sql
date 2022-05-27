create table students
(
    id         bigserial primary key,
    name      varchar(255),
    age      numeric(2, 0) not null
);

insert into students (name, age)
values ('Вано', 42),
       ('Диман', 42),
       ('Клавка', 36),
       ('Серый', 42),
       ('Леша', 35),
       ('Гарик', 43);
