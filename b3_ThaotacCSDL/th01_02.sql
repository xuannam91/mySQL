CREATE DATABASE ss03_th01_02;
USE ss03_th01_02;
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

-- hiển thị toàn bộ danh sách học viên
SELECT *
FROM Student;

-- hiển thị toàn bộ danh sách học viên đang học
SELECT * FROM Student WHERE Status = 1;


--  Hiển thị danh sách các môn học có thời gian học nhỏ hơn 10 giờ.
SELECT * FROM Subject WHERE Credit <10;

-- Hiển thị danh sách học viên lớp A1

SELECT StudentId, StudentName, ClassName
FROM Student JOIN Class ON Student.ClassID = Class.ClassID
WHERE ClassName = 'A1';

 -- Hiển thị điểm môn CF của các học viên.

SELECT S.StudentId, S.StudentName, Sub.SubName, M.Mark
FROM Student S
JOIN Mark M on S.StudentId = M.StudentId
JOIN Subject Sub on M.SubId = Sub.SubId
WHERE Sub.SubName = 'CF';

