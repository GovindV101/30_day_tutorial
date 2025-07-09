create database RetailDB;

use RetailDB;

CREATE TABLE products (
 productId INT PRIMARY KEY,
 name VARCHAR(100),
 category VARCHAR(50),
 price DOUBLE,
 stockQuantity INT
);

select * from products;

drop table products;