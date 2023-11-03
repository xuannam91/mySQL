CREATE DATABASE order_management;
USE order_management;
CREATE TABLE Product(
    product_id INT AUTO_INCREMENT PRIMARY KEY ,
    product_name VARCHAR(50) NOT NULL ,
    price DOUBLE
);

CREATE TABLE Orders(
    order_id INT AUTO_INCREMENT PRIMARY KEY ,
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    quantity INT,
    order_date DATETIME DEFAULT(curdate())
);

INSERT INTO Product(product_name, price)
VALUES('ao',5.5);
INSERT INTO Product(product_name, price)
VALUES('quan',10.5);
INSERT INTO Product(product_name, price)
VALUES('mu',2.5);

INSERT INTO Orders(product_id, quantity)
VALUES (2,15);
INSERT INTO Orders(product_id, quantity)
VALUES (1,20);
INSERT INTO Orders(product_id, quantity)
VALUES (2,5);
INSERT INTO Orders(product_id, quantity)
VALUES (3,50);
INSERT INTO Orders(product_id, quantity)
VALUES (1,30);


SELECT Orders.order_id, Product.product_name, Orders.quantity, Product.price * Orders.quantity AS 'total_money'
FROM Product JOIN Orders ON Product.product_id = Orders.product_id ;

SELECT Orders.order_id, Product.product_name, Orders.quantity, Product.price * Orders.quantity AS 'total_money'
FROM Product JOIN Orders ON Product.product_id = Orders.product_id
WHERE (Product.price * Orders.quantity) > 100;

SELECT Orders.order_id, Product.product_name, Orders.quantity, Product.price * Orders.quantity AS 'total_money'
FROM Product JOIN Orders ON Product.product_id = Orders.product_id
WHERE Product.product_name LIKE '%u%';


