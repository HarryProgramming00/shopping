CREATE SCHEMA IF NOT EXISTS orders;
SET SCHEMA orders;

CREATE TABLE orders (
    id UUID NOT NULL,
    customer_id UUID NOT NULL,
    product_id UUID NOT NULL
);

CREATE TABLE products (
    id UUID NOT NULL,
    name varchar(50) NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL
);


--Insert some test data

--Orders
insert into orders(id, customer_id, product_id) values ('77f9ada7-d366-47a6-8ba1-eebfdac7e70a','c2c5ad6c-28a4-4ce3-a58f-9f2073e0a5b3','840085abeca64ee3ae6579f3950da1f9');

--Products
insert into products(id, name, unit_price) values ('840085ab-eca6-4ee3-ae65-79f3950da1f9','X-Box 360', 299.99);