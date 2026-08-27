drop database if exists SmartLocker;
create database SmartLocker;
use SmartLocker;

CREATE TABLE Users (
  u_no int auto_increment,
  constraint primary key (u_no),
  u_pwd varchar(15) not null,
  u_phone varchar(20) not null,
  u_name varchar(20) not null,
  u_grade varchar(10) default 'user',
  u_student_id varchar(20) not null unique
);

CREATE TABLE Equipment (
    e_no  int auto_increment ,
    e_name varchar(50) not null,
    e_category varchar(30),
    e_status varchar(20) DEFAULT '대여가능',
    lno int,
    CONSTRAINT pk_equipment PRIMARY KEY (e_no)
);

CREATE TABLE Locker (
    l_no int auto_increment ,
    l_location varchar(20) not null ,
    l_status varchar(10) DEFAULT '닫힘',
    PRIMARY KEY (l_no)
);

create table Rental (
    r_no int auto_increment,
    u_no int not null,
    e_no int not null,
    r_date datetime not null default now(),
    r_due_date datetime not null,
    r_return_date datetime null,
    r_status varchar(10) default '대여중',
    r_condition varchar(10) null,

    constraint primary key (r_no),
    constraint foreign key (u_no) references Users (u_no),
    constraint foreign key (e_no) references Equipment (e_no)
);

create table Report (
    report_id int auto_increment,
    r_no int not null,
    report_type varchar(20) not null,
    description text not null,
    created_at datetime default now(),
    status varchar(20) default '접수',

    constraint primary key (report_id),
    constraint foreign key (r_no) references rental (r_no)
);

use equipmentrental;

desc report;
desc rental;

select * from rental where r_no = 1;