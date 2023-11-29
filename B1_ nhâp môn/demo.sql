-- 1 . THAO TÁC VỚI CẤU TRÚC
-- câu lệnh tạo mới 1 schema
CREATE DATABASE demo;
-- Phải sư dụng database nào để thao tác
USE demo; 
-- câu lệnh tạo mới 1 bảng
create table category(
category_id int auto_increment primary key,
category_name varchar(50)
);
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50),
    price FLOAT,
    quantity INT,
    category_id INT,
    FOREIGN KEY (`category_id`)
        REFERENCES `category` (`category_id`)
);
-- câu lệnh thêm mới bản ghi có mục đích trong mysql
INSERT INTO category (category_name)
VALUES ('Iphone'), ('Mac'), ('Apple Watch');
INSERT INTO product (product_name, price)
VALUES ('Iphone 11',30), ('Mac pro',400), ('Apple Watch series 2',200);
-- Lệnh thêm mới tất cả các cột trong mysql
INSERT INTO product(product_name, price, quantity, category_id)
VALUES ('Iphone 13 prm', 200, 10, 1),('Mac 15 prm', 200, 10, 1) ;

-- câu lệnh xoá database
-- drop database demo;
-- câu lệnh xoá bảng
-- ví dụ
-- drop table product;


-- Thao tác sửa db 
-- thêm mới cột vào bảng
ALTER TABLE product
ADD descriptions text;

-- xoá cột trong db
ALTER TABLE product
DROP COLUMN descriptions;

-- Đổi tên bảng
ALTER TABLE product
RENAME products;

-- đổi tên cột
ALTER TABLE products
CHANGE price prices int;

-- thay đổi kiểu dữ liệu của cột
ALTER TABLE products
MODIFY prices float;


-- thêm khoá chính cho bảng
ALTER TABLE cart
ADD PRIMARY KEY(id);

create table cart(
id int,
product_id int,
quantity int
);

-- Xoá khoá chính
ALTER TABLE cart
DROP PRIMARY KEY;
	
-- Thêm khoá ngoại
ALTER TABLE cart
ADD FOREIGN KEY (`product_id`)
        REFERENCES `products`(`id`);

-- xoá khoá ngoại
ALTER TABLE cart
DROP FOREIGN KEY cart_ibfk_1;
        
        
        
-- 2. THAO TÁC VỚI DỮ LIỆU
-- thay đổi dữ liệu
UPDATE products 
SET product_name = 'Uy', price = 30, quantity = 20, category_id = 2
WHERE id = 1;

select * from products;

-- Xoá bản ghi
DELETE FROM products WHERE id = 1;








