CREATE DATABASE ss02_bt02;
USE ss02_bt02;
create table  customer (
    customer_id int auto_increment primary key,
    customer_name varchar(20) not null,
    customer_age int check(customer_age >0) not null
);

create table product (
    product_id int auto_increment primary key,
    product_name varchar(50) not null,
    product_price int check(product_price >= 0) not null
);

create table orders (
   order_id int auto_increment primary key,
   order_date timestamp default current_timestamp,
    order_totalPrice int not null,
   customer_id int,
   foreign key (customer_id) references customer(customer_id)
);

create table orderDetail(
    order_id int,
    product_id int,
    orderQuantity int,
    primary key (order_id, product_id),
    foreign key (order_id) references orders (order_id),
    foreign key (product_id) references product (product_id)
);

