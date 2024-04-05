CREATE SCHEMA IF NOT EXISTS customer;
SET SCHEMA customer;

CREATE TABLE customer (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL
);

--Insert test data
insert into customer(id, name) values ('c2c5ad6c-28a4-4ce3-a58f-9f2073e0a5b3','Harry Farmer');