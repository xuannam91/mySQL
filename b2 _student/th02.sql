CREATE DATABASE ss02_th02;
USE ss02_th02;
CREATE TABLE Class(
    ClassID INT not null auto_increment primary key ,
    ClassName varchar(60) not null ,
    StartDate DATETIME not null ,
    Status    BIT
);

CREATE TABLE Student(
    StudentId int not null auto_increment primary key ,
    StudentName varchar(30) not null ,
    Address varchar(50),
    Phone varchar(20),
    Status      BIT,
    ClassId INT not null ,
    FOREIGN KEY (ClassId) REFERENCES Class(ClassID)
);

CREATE TABLE Subject(
    SubId   INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    SubName VARCHAR(30) not null ,
    Credit TINYINT NOT NULL DEFAULT 1 CHECK (Credit >= 1),
    Status BIT DEFAULT 1
);

CREATE TABLE Mark(
    MarkId INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    SubId INT NOT NULL ,
    StudentId INT NOT NULL ,
    Mark FLOAT DEFAULT 0 CHECK ( Mark BETWEEN 0 AND 100 ),
    ExamTimes TINYINT DEFAULT 1,
    UNIQUE (SubId, StudentId),
    FOREIGN KEY (SubId)REFERENCES Subject(SubId),
    FOREIGN KEY (StudentId)REFERENCES Student(StudentId)
);
