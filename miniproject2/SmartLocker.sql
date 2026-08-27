-- ============================================
-- SmartLocker 프로젝트 DB 전체 초기화
-- ============================================

DROP DATABASE IF EXISTS SmartLocker;
CREATE DATABASE SmartLocker;
USE SmartLocker;


-- ============================================
-- 1. 회원 테이블
-- ============================================
CREATE TABLE Users (
    u_no INT AUTO_INCREMENT,
    u_pwd VARCHAR(15) NOT NULL,
    u_phone VARCHAR(20) NOT NULL,
    u_name VARCHAR(20) NOT NULL,
    u_grade VARCHAR(10) DEFAULT 'user',
    u_student_id VARCHAR(20) NOT NULL UNIQUE,

    CONSTRAINT pk_users PRIMARY KEY (u_no)
);


-- ============================================
-- 2. 보관함 테이블
-- ============================================
CREATE TABLE Locker (
    l_no INT AUTO_INCREMENT,
    l_location VARCHAR(20) NOT NULL,
    l_status VARCHAR(10) DEFAULT '닫힘',

    CONSTRAINT pk_locker PRIMARY KEY (l_no)
);


-- ============================================
-- 3. 장비 테이블
-- ============================================
CREATE TABLE Equipment (
    e_no  int auto_increment ,
    e_name varchar(50) not null,
    e_category varchar(30),
    e_status varchar(20) DEFAULT '대여가능',
    l_no int,
    CONSTRAINT pk_equipment PRIMARY KEY (e_no)
);

create table Rental (
    r_no int auto_increment ,
    u_no int not null ,
    e_no int not null,
    r_date datetime not null default now(),
    r_due_date datetime not null DEFAULT (now() + interval 2 day),
    r_return_date datetime null ,
    r_status varchar(10) default '대여중',
    r_condition varchar(10) null,

    CONSTRAINT pk_rental PRIMARY KEY (r_no),

    CONSTRAINT fk_rental_users
        FOREIGN KEY (u_no)
        REFERENCES Users(u_no),

    CONSTRAINT fk_rental_equipment
        FOREIGN KEY (e_no)
        REFERENCES Equipment(e_no)
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

-- user 테이블 샘플 데이터
insert into Users (u_no, u_pwd, u_phone, u_name, u_grade, u_student_id) values
(1, '1234', '010-1234-5678', '관리자', 'admin', '1'),
(2, '141543', '010-1431-5345', '야스오', 'user', '2020121'),
(3, 'pw123', '010-4321-5678', '요네', 'user', '2020122'),
(4, 'pw432', '010-9876-1234', '탑베인', 'user', '2021120'),
(5, 'pw000', '010-5678-9876', '말파이트', 'user', '2021121'),
(6, 'pw001', '010-1212-3434', '나르', 'user', '2022120'),
(7, 'pw4321', '010-6666-7777', '티모', 'user', '2022121'),
(8, 'pw876', '010-7878-8787', '모르가나', 'user', '2023120'),
(9, 'pw555', '010-9988-7766', '쉬바나', 'user', '2023121'),
(10, 'pw789', '010-9000-0000', '아우렐리온 솔', 'user', '2024120');


INSERT INTO locker (l_location, l_status)
VALUES
('성결관B102-01', '닫힘'),
('성결관B102-02', '닫힘'),
('성결관B102-03', '닫힘'),
('성결관B102-04', '닫힘'),
('성결관B102-05', '닫힘'),
('성결관B102-06', '닫힘'),
('성결관B102-07', '닫힘'),
('성결관B102-08', '닫힘'),
('성결관B102-09', '닫힘'),
('성결관B102-10', '닫힘');

-- Equipment 테이블 샘플 데이터
INSERT INTO Equipment (e_name, e_category, e_status, l_no) VALUES
('LG그램', '노트북', '대여가능', 1),
('LG그램', '노트북', '대여중', 2),
('갤럭시탭 S9', '태블릿', '대여중', 3),
('갤럭시탭 S9', '태블릿', '점검필요', 4),
('Arduino UNO Kit', '아두이노', '대여가능', 5),
('Arduino UNO Kit', '아두이노', '수리중', 6),
('Raspberry Pi 5', '라즈베리파이', '대여가능', 7),
('미러리스 카메라', '카메라', '대여중', 8),
('빔프로젝터', '프로젝터', '대여중', 9),
('캡처보드', '캡처보드', '대여가능', 10);

-- Rental 테이블 샘플 데이터
insert into Rental
(u_no, e_no, r_date, r_due_date, r_return_date, r_status, r_condition)
values
(2, 3, '2026-08-19 14:30:00', '2026-08-21 14:30:00', null, '대여중', null),
(4, 5, '2026-08-15 09:10:00', '2026-08-18 09:10:00', '2026-08-17 16:20:00', '반납완료', '정상'),
(6, 7, '2026-08-10 11:00:00', '2026-08-13 11:00:00', '2026-08-15 10:45:00', '반납완료', '이상있음'),
(8, 9, '2026-08-20 13:15:00', '2026-08-23 13:15:00', null, '대여중', null),
(10, 1, '2026-08-05 10:00:00', '2026-08-08 10:00:00', '2026-08-08 09:30:00', '반납완료', '정상'),
(3, 8, '2026-08-01 15:40:00', '2026-08-04 15:40:00', null, '연체', null),
(5, 4, '2026-08-12 08:50:00', '2026-08-15 08:50:00', '2026-08-14 17:00:00', '반납완료', '정상'),
(7, 2, '2026-08-18 16:05:00', '2026-08-21 16:05:00', null, '대여중', null),
(1, 10, '2026-07-28 12:20:00', '2026-07-31 12:20:00', '2026-08-03 11:10:00', '반납완료', '이상있음'),
(9, 6, '2026-08-14 09:45:00', '2026-08-17 09:45:00', '2026-08-16 14:00:00', '반납완료', '정상');



insert into report
(r_no, report_type, description, status)
values
(5, '고장', '키보드 일부 키가 입력되지 않습니다.', '접수'),
(3, '고장', 'HDMI 출력이 정상적으로 되지 않습니다.', '점검중'),
(7, '파손', '태블릿 화면 모서리에 금이 갔습니다.', '수리중'),
(10, '고장', '아두이노 보드가 과열되고 탄 냄새가 납니다.', '처리완료'),
(9, '파손', '캡처보드 HDMI 단자가 휘어졌습니다.', '접수'),
(2, '고장', '아두이노 USB 연결이 간헐적으로 끊깁니다.', '점검중'),
(3, '고장', '라즈베리파이 전원이 정상적으로 돌아오지 않습니다.', '처리완료'),
(5, '파손', '노트북 충전 단자가 손상되었습니다.', '수리중'),
(7, '고장', '태블릿 터치 입력이 일부 영역에서 되지 않습니다.', '처리완료'),
(10, '파손', '아두이노 보드의 USB 단자가 파손되었습니다.', '접수');