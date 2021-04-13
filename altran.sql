create database altran;

use altran;

create table operations(
operation varchar(20) not null,
value1 int not null,
value2 int not null,
result int not null,
CONSTRAINT COMP_KEY PRIMARY KEY (operation, value1, value2));
