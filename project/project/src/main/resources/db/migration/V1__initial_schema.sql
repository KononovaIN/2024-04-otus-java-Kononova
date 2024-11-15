create table Book_types
(
    id   bigserial not null primary key,
    name varchar,
    cnt bigint,
    fine bigint,
    day_count bigint
);

create table Books
(
    id   bigserial not null primary key,
    name varchar,
    cnt bigint,
    typeId bigint references Book_types(id)
);

create table Clients
(
    id   bigserial not null primary key,
    firstName varchar,
    lastName varchar,
    patherName varchar,
    passportSeria varchar,
    passportNum varchar
);

create table Journal
(
    id   bigserial not null primary key,
    bookId bigint  references Books(id),
    clientId bigint  references Clients(id),
    dateBeg date,
    dateEnd date,
    dateRet date
);


create table Users
(
    id   bigserial not null primary key,
    user_name varchar,
    password varchar
);