CREATE DATABASE ss04_th01_bt01;
USE ss04_th01_bt01;
CREATE TABLE Class(
                      ClassID INT PRIMARY KEY,
                      ClassName VARCHAR(30) NOT NULL ,
                      StartDate DATE,
                      Status INT
);

CREATE TABLE Student (
                         StudentID INT AUTO_INCREMENT PRIMARY KEY ,
                         StudentName VARCHAR(20) not null,
                         Address VARCHAR(40) not null ,
                         Phone VARCHAR(10) ,
                         Status INT,
                         ClassID INT,
                         FOREIGN KEY (ClassID) REFERENCES Class(ClassID)
);

CREATE TABLE Subject(
                        SubID INT PRIMARY KEY ,
                        SubName VARCHAR(30),
                        Credit INT,
                        Status INT
);

CREATE TABLE Mark(
                     MarkID INT AUTO_INCREMENT PRIMARY KEY,
                     SubID INT,
                     StudentID INT,
                     Mark INT,
                     ExamTimes INT,
                     FOREIGN KEY (SubID)REFERENCES Subject(SubID),
                     FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
);

INSERT INTO Class
VALUES (1, 'A1', '2008-12-20', 1);
INSERT INTO Class
VALUES (2, 'A2', '2008-12-22', 1);
INSERT INTO Class
VALUES (3, 'B3', current_date, 0);


INSERT INTO Student (StudentName, Address, Phone, Status, ClassID)
VALUES ('Hung', 'Ha Noi', '0912113113', 1, 1);
INSERT INTO Student (StudentName, Address, Status, ClassID)
VALUES ('Hoa', 'Hai phong', 1, 1);
INSERT INTO Student (StudentName, Address, Phone, Status, ClassID)
VALUES ('Manh', 'HCM', '0123123123', 0, 2);
INSERT INTO Student (StudentName, Address, Phone, Status, ClassID)
VALUES ('Tuan', 'HCM', '0123123123', 0, 2);
INSERT INTO Student (StudentName, Address, Phone, Status, ClassID)
VALUES ('Binh', 'Ha Noi', '0123123123', 0, 1);
INSERT INTO Student (StudentName, Address, Phone, Status, ClassID)
VALUES ('Huy', 'HCM', '0123123123', 0, 2);


INSERT INTO Subject
VALUES (1, 'CF', 5, 1),
       (2, 'C', 6, 1),
       (3, 'HDJ', 5, 1),
       (4, 'RDBMS', 10, 1),
       (5, 'Toan', 10, 1);

INSERT INTO Mark (SubId, StudentId, Mark, ExamTimes)
VALUES (1, 1, 8, 1),
       (1, 2, 10, 2),
       (2, 1, 5, 1),
       (5, 1, 10, 1),
       (2, 1, 7, 1);


-- Hiển thị số lượng sinh viên ở từng nơi.
SELECT COUNT(StudentID) AS 'Số lượng học viên', Address
FROM Student
GROUP BY Address;


-- Tính điểm trung bình các môn học của mỗi học viên bằng cách sử dụng hàm AVG;
SELECT s.StudentId, s.StudentName ,AVG(m.Mark) AS 'Điểm trung bình '
FROM Student s JOIN  Mark m ON s.StudentID = m.StudentID
GROUP BY s.StudentID;


-- Hiển thị những bạn học viên có điểm trung bình các môn học lớn hơn 7
SELECT s.StudentId, s.StudentName ,AVG(m.Mark) AS 'Điểm trung bình '
FROM Student s JOIN  Mark m ON s.StudentID = m.StudentID
GROUP BY s.StudentID
HAVING AVG(m.Mark) > 7;


--  Hiển thị thông tin các học viên có điểm trung bình lớn nhất.
SELECT
    MAX(diem_trung_binh_hv.diem_trung_binh) max_avg
FROM (
         SELECT s.StudentId, s.StudentName ,AVG(m.Mark) AS diem_trung_binh
         FROM Student s JOIN  Mark m ON s.StudentID = m.StudentID
         GROUP BY s.StudentID
     ) AS diem_trung_binh_hv;


--  Hiển thị thông tin các học viên có điểm trung bình nhỏ nhất.
SELECT
    MIN(diem_trung_binh_hv.diem_trung_binh) AS min_avg
FROM (
         SELECT s.StudentId, s.StudentName ,AVG(m.Mark) AS diem_trung_binh
         FROM Student s JOIN  Mark m ON s.StudentID = m.StudentID
         GROUP BY s.StudentID
     ) AS diem_trung_binh_hv;



-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
SELECT * FROM Subject WHERE Credit = (SELECT MAX(Subject.Credit) FROM Subject);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
SELECT su.SubID, su.SubName, m.Mark FROM Subject su JOIN Mark m ON su.SubID = m.SubID
                      WHERE m.Mark = (SELECT MAX(Mark.Mark) FROM Mark);

-- • Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
SELECT
    s.StudentID,
    s.StudentName,
    AVG(m.Mark) AS diem_trung_binh
    FROM Student s JOIN Mark m ON s.StudentID = m.StudentID
    GROUP BY s.StudentID
    ORDER BY diem_trung_binh DESC;