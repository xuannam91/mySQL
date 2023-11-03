-- DROP database if exists student_management;
CREATE DATABASE student_management;
USE student_management;
-- tạo bảng category;
CREATE TABLE class (
class_id int auto_increment primary key,
class_name VARCHAR(30) NOT NULL
);
-- tạo bảng sinh viên
CREATE TABLE student(
student_id int auto_increment primary key,
student_name varchar(30) NOT NULL,
student_age INT CHECK (student_age > 0) NOT NULL,
class_id INT
);

-- ràng buộc class với student
ALTER TABLE student
ADD CONSTRAINT fk_class FOREIGN KEY (`class_id`)
        REFERENCES `class`(`class_id`);
        
        
        
-- tạo bảng môn học 
CREATE TABLE subject(
subject_id int auto_increment primary key,
subject_name varchar(30) NOT NULL
);     

-- bảng nối sinh viên với môn học
CREATE TABLE subject_student (
student_id INT,
subject_id INT
);



-- Tạo khoá chính cho bảng subject_student
ALTER TABLE subject_student
ADD CONSTRAINT pk_student_subject PRIMARY KEY(student_id,subject_id);

-- ràng buộc bảng nối với student
ALTER TABLE subject_student
ADD CONSTRAINT fk_student FOREIGN KEY (`student_id`)
        REFERENCES `student`(`student_id`); 
        
-- ràng buộc bảng nối với subject
ALTER TABLE subject_student
ADD CONSTRAINT fk_subject FOREIGN KEY (`subject_id`)
        REFERENCES `subject`(`subject_id`); 
        
-- thêm cột điểm
ALTER TABLE subject_student
ADD COLUMN mark INT DEFAULT 0;
        
        
-- Lấy ra danh sách id của sinh viên theo 1 lớp
SELECT student_id FROM student WHERE class_id = 1;

-- Lấy ra danh sách điểm của 1 sinh viên 
SELECT mark FROM student_subject WHERE student_id = 1;

-- Láy ra danh id của sinh viên viên theo 1 môn học đã đăng ký
SELECT student_id FROM student_subject WHERE subject_id = 1;
        