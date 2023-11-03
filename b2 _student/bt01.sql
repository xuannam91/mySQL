CREATE DATABASE ss2_bt01;
use ss2_bt01;
create table NhaCC
(
    id_ncc     int not null auto_increment primary key,
    ten_ncc    varchar(40),
    phone_ncc  varchar(10),
    diachi_ncc varchar(100)
);

create table donDH
(
    so_DH   int not null auto_increment primary key,
    ngaydat datetime,
    id_ncc  int,
    foreign key (id_ncc) references NhaCC (id_ncc)
);

create table Vattu
(
    Ma_VT  int auto_increment primary key,
    Ten_VT varchar(50)
);
create table PX
(
    soPX     int auto_increment primary key,
    ngayxuat datetime
);

create table PN
(
    soPN     int auto_increment primary key,
    ngaynhap datetime
);

create table PN_VT
(
    Ma_VT   int,
    So_PN   int,
    DG_Nhap float,
    SL_Nhap int,
    primary key (Ma_VT, So_PN),
    foreign key (So_PN) references PN (soPN),
    foreign key (Ma_VT) references Vattu (Ma_VT)
);

create table PX_VT
(
    Ma_VT   int,
    So_PX   int,
    DG_Xuat float,
    SL_Xuat int,
    primary key (Ma_VT, so_PX),
    foreign key (Ma_VT) references Vattu (Ma_VT),
    foreign key (So_PX) references PX (soPX)
);


create table donhang_VT
(
    so_DH int,
    Ma_VT int,
    primary key (so_DH, Ma_VT),
    foreign key (so_DH) references donDH (so_DH),
    foreign key (Ma_VT) references Vattu (Ma_VT)
);