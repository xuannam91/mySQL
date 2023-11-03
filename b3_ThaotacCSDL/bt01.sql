CREATE DATABASE ss03_bt01_qlsv;
USE ss03_bt01_qlsv;
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


INSERT INTO Subject
VALUES (1, 'CF', 5, 1),
       (2, 'C', 6, 1),
       (3, 'HDJ', 5, 1),
       (4, 'RDBMS', 10, 1);

INSERT INTO Mark (SubId, StudentId, Mark, ExamTimes)
VALUES (1, 1, 8, 1),
       (1, 2, 10, 2),
       (2, 1, 12, 1);


-- Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’
SELECT
    s.StudentID,
    s.StudentName
FROM Student s
WHERE StudentName LIKE '%h%';


--  Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.

SELECT * FROM Class WHERE MONTH(StartDate) = 12;

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 6-10.

SELECT * FROM Subject WHERE Credit BETWEEN 6 AND 10;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2

UPDATE Student
set ClassID = 1 WHERE StudentName = 'Hung';

-- Hiển thị các thông tin: StudentName, SubName, Mark.
 -- Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.

SELECT
    s.StudentName,
    su.SubName,
    m.Mark
FROM Student s JOIN Mark m ON s.StudentID = m.StudentID
JOIN Subject su ON m.SubID = su.SubID
ORDER BY m.Mark DESC, s.StudentName;