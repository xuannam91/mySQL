CREATE DATABASE ss02_th01;
USE ss02_th01;
CREATE TABLE Hocsinh(
    MaHS varchar(20) primary key ,
    TenHS varchar(50),
    NgaySinh DATETIME,
    Lop varchar(20),
    GT varchar(20)
);

CREATE TABLE MonHoc(
    MaMH varchar(20) primary key ,
    TenMH varchar(50)
);

CREATE TABLE BangDiem(
     MaHS VARCHAR(20),
     MaMH VARCHAR(20),
     DiemThi INT,
     NgayKT DATETIME,
     PRIMARY KEY (MaHS,MaMH),
     FOREIGN KEY (MaHS) references Hocsinh(MaHS),
     FOREIGN KEY (MaMH) references MonHoc(MaMH)
);

CREATE TABLE GiaoVien(
    MaGV varchar(20) primary key ,
    TenGV varchar(20),
    SDT varchar(10)
);

ALTER TABLE MonHoc
ADD MaGV varchar(20);

ALTER TABLE MonHoc
ADD CONSTRAINT FK_MaGV FOREIGN KEY(MaGV) REFERENCES GiaoVien(MaGV);

