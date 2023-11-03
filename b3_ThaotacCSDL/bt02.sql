CREATE DATABASE ss03_bt02_qlbh;
USE ss03_bt02_qlbh;
CREATE TABLE CUSTOMERS(
        CUSTOMERS_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        CUSTOMERS_NAME VARCHAR(40) NOT NULL,
        CUSTOMERS_AGE  INT(3) NOT NULL
);

CREATE TABLE ORDERS(
       ORDERS_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
       CUSTOMERS_ID INT,
       ORDER_DATE   DATE,
       TOTAL_PRICE  DOUBLE,
       CONSTRAINT FK_CUSTOMER FOREIGN KEY (CUSTOMERS_ID) REFERENCES CUSTOMERS (CUSTOMERS_ID)
);

CREATE TABLE PRODUCT
(
    PRODUCT_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(40),
    PRICE DOUBLE
);


CREATE TABLE ORDER_DETAIL(
         ORDERS_ID   INT,
         PRODUCT_ID INT,
         ORDER_QTY  INT,
         PRIMARY KEY (ORDERS_ID, PRODUCT_ID),
         CONSTRAINT FK_ORDER FOREIGN KEY (ORDERS_ID) REFERENCES ORDERS (ORDERS_ID),
         CONSTRAINT FK_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
);


INSERT INTO CUSTOMERS
    VALUE (1, 'Minh Quan', 10),
    (2, 'Ngoc Oanh', 20),
    (3, 'Hong Ha', 50),
    (4, 'Hung', 30);


INSERT INTO ORDERS
    VALUE (1, 1, STR_TO_DATE('2006-03-21', '%Y-%m-%d'), NULL),
    (2, 2, STR_TO_DATE('2006-03-23', '%Y-%m-%d'), NULL),
    (3, 1, STR_TO_DATE('2006-03-16', '%Y-%m-%d'), NULL);

INSERT INTO PRODUCT
    VALUE (1, 'May Giat', 3),
    (2, 'Tu Lanh', 5),
    (3, 'Dieu Hoa', 7),
    (4, 'Quat', 1),
    (5, 'Bep Dien', 2);

INSERT INTO ORDER_DETAIL
    VALUE (1, 1, 3),
    (1, 3, 7),
    (1, 4, 2),
    (2, 1, 1),
    (3, 1, 8),
    (2, 5, 4),
    (2, 3, 3);


-- Lấy ra các order bao gồm orderid, tên sản phẩm, số lượng và đơn giá cũng như tổng tiền
SELECT
    o.ORDERS_ID,
    p.NAME,
    p.PRICE,
    od.ORDER_QTY,
    od.ORDER_QTY * p.PRICE TOTAL_PRICE
FROM ORDERS o JOIN ORDER_DETAIL od ON o.ORDERS_ID = od.ORDERS_ID
JOIN PRODUCT p ON  od.PRODUCT_ID = p.PRODUCT_ID;


-- hiện order idorder ngày, tổng thanh toán của từng người
SELECT
    o.ORDERS_ID,
    c.CUSTOMERS_NAME,
    o.ORDER_DATE,
    SUM(od.ORDER_QTY * p.PRICE) AS TOTAL_PRICE
FROM ORDERS o JOIN CUSTOMERS c ON o.CUSTOMERS_ID = c.CUSTOMERS_ID
JOIN ORDER_DETAIL od ON o.ORDERS_ID = od.ORDERS_ID
JOIN PRODUCT p ON od.PRODUCT_ID = p.PRODUCT_ID
GROUP BY o.ORDERS_ID;


-- Hiển thị khách hàng đã mua hàng, và những sản phẩm đã mua
SELECT
    c.CUSTOMERS_ID,
    c.CUSTOMERS_NAME,
    GROUP_CONCAT(p.NAME SEPARATOR ', ')
    FROM CUSTOMERS c JOIN ORDERS o ON c.CUSTOMERS_ID = o.CUSTOMERS_ID
    JOIN ORDER_DETAIL od ON o.ORDERS_ID = od.ORDERS_ID
    JOIN PRODUCT p ON od.PRODUCT_ID = p.PRODUCT_ID
    GROUP BY c.CUSTOMERS_ID;

-- Hiển thị những người không mua hàng

    SELECT
        c.CUSTOMERS_ID,
        c.CUSTOMERS_NAME
    FROM CUSTOMERS AS c
             LEFT JOIN ORDERS AS o ON c.CUSTOMERS_ID = o.CUSTOMERS_ID
    WHERE o.ORDERS_ID IS NULL;