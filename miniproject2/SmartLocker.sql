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
    e_no INT AUTO_INCREMENT,
    e_name VARCHAR(50) NOT NULL,
    e_category VARCHAR(30),
    e_status VARCHAR(20) DEFAULT '대여가능',
    l_no INT,

    CONSTRAINT pk_equipment PRIMARY KEY (e_no),

    CONSTRAINT fk_equipment_locker
        FOREIGN KEY (l_no)
        REFERENCES Locker(l_no)
);


-- ============================================
-- 4. 대여 테이블
-- ============================================
CREATE TABLE Rental (
    r_no INT AUTO_INCREMENT,
    u_no INT NOT NULL,
    e_no INT NOT NULL,

    r_date DATETIME NOT NULL DEFAULT NOW(),

    r_due_date DATETIME NOT NULL
        DEFAULT (NOW() + INTERVAL 2 DAY),

    r_return_date DATETIME NULL,

    r_status VARCHAR(10) DEFAULT '대여중',

    r_condition VARCHAR(10) NULL,

    CONSTRAINT pk_rental PRIMARY KEY (r_no),

    CONSTRAINT fk_rental_users
        FOREIGN KEY (u_no)
        REFERENCES Users(u_no),

    CONSTRAINT fk_rental_equipment
        FOREIGN KEY (e_no)
        REFERENCES Equipment(e_no)
);


-- ============================================
-- 5. 고장 / 파손 신고 테이블
-- ============================================
CREATE TABLE Report (
    report_id INT AUTO_INCREMENT,

    r_no INT NOT NULL,

    report_type VARCHAR(20) NOT NULL,

    description TEXT NOT NULL,

    status VARCHAR(20) DEFAULT '접수',

    CONSTRAINT pk_report PRIMARY KEY (report_id),

    CONSTRAINT fk_report_rental
        FOREIGN KEY (r_no)
        REFERENCES Rental(r_no)
);


-- ============================================
-- 회원 샘플 데이터 10개
-- ============================================
INSERT INTO Users
(u_pwd, u_phone, u_name, u_grade, u_student_id)
VALUES
('1234', '010-1111-1111', '김민수', 'user', '20260001'),
('1234', '010-2222-2222', '이영희', 'user', '20260002'),
('1234', '010-3333-3333', '박철수', 'user', '20260003'),
('1234', '010-4444-4444', '최지우', 'user', '20260004'),
('1234', '010-5555-5555', '정현우', 'user', '20260005'),
('1234', '010-6666-6666', '강수진', 'user', '20260006'),
('1234', '010-7777-7777', '윤동현', 'user', '20260007'),
('1234', '010-8888-8888', '한지민', 'user', '20260008'),
('1234', '010-9999-9999', '송하늘', 'user', '20260009'),
('admin123', '010-0000-0000', '관리자', 'admin', 'ADMIN001');


-- ============================================
-- 보관함 샘플 데이터 10개
-- ============================================
INSERT INTO Locker
(l_location, l_status)
VALUES
('A-01', '닫힘'),
('A-02', '닫힘'),
('A-03', '닫힘'),
('A-04', '닫힘'),
('A-05', '닫힘'),
('B-01', '닫힘'),
('B-02', '닫힘'),
('B-03', '닫힘'),
('B-04', '닫힘'),
('B-05', '닫힘');


-- ============================================
-- 장비 샘플 데이터 10개
-- ============================================
INSERT INTO Equipment
(e_name, e_category, e_status, l_no)
VALUES
('노트북', '컴퓨터', '대여가능', 1),
('태블릿', '모바일', '대여가능', 2),
('라즈베리파이', '개발보드', '대여가능', 3),
('아두이노', '개발보드', '대여가능', 4),
('키보드', '주변기기', '대여가능', 5),
('마우스', '주변기기', '대여가능', 6),
('캡처보드', '영상장비', '대여가능', 7),
('웹캠', '영상장비', '대여가능', 8),
('모니터', '디스플레이', '대여가능', 9),
('프로젝터', '영상장비', '대여가능', 10);


-- ============================================
-- 대여 샘플 데이터 10개
-- ============================================
INSERT INTO Rental
(u_no, e_no, r_date, r_due_date, r_return_date, r_status, r_condition)
VALUES
(1, 2, '2026-08-01 09:00:00', '2026-08-03 09:00:00',
 '2026-08-03 10:00:00', '반납완료', '이상있음'),

(2, 4, '2026-08-05 11:00:00', '2026-08-07 11:00:00',
 '2026-08-07 12:30:00', '반납완료', '이상있음'),

(3, 3, '2026-08-10 09:30:00', '2026-08-12 09:30:00',
 '2026-08-12 10:45:00', '반납완료', '이상있음'),

(4, 2, '2026-08-11 14:00:00', '2026-08-13 14:00:00',
 '2026-08-13 17:00:00', '반납완료', '이상있음'),

(5, 5, '2026-08-06 08:30:00', '2026-08-08 08:30:00',
 '2026-08-08 09:30:00', '반납완료', '이상있음'),

(6, 7, '2026-08-02 10:00:00', '2026-08-04 10:00:00',
 '2026-08-04 11:10:00', '반납완료', '이상있음'),

(7, 1, '2026-08-15 09:00:00', '2026-08-17 09:00:00',
 '2026-08-17 16:20:00', '반납완료', '정상'),

(8, 8, '2026-08-18 09:00:00', '2026-08-20 09:00:00',
 '2026-08-20 13:00:00', '반납완료', '정상'),

(9, 10, '2026-08-14 12:00:00', '2026-08-16 12:00:00',
 '2026-08-16 14:00:00', '반납완료', '이상있음'),

(1, 2, '2026-08-27 15:00:00', '2026-08-29 15:00:00',
 NULL, '대여중', NULL);


-- ============================================
-- 신고 샘플 데이터 10개
-- 상태값: 접수 / 점검중 / 수리중 / 처리완료
-- ============================================
INSERT INTO Report
(r_no, report_type, description, status)
VALUES
(1, '파손', '태블릿 화면 모서리에 금이 갔습니다.', '접수'),

(2, '고장', '아두이노 USB 연결이 간헐적으로 끊깁니다.', '점검중'),

(3, '고장', '라즈베리파이 전원이 정상적으로 들어오지 않습니다.', '수리중'),

(4, '파손', '태블릿 터치 영역 일부가 손상되었습니다.', '처리완료'),

(5, '고장', '키보드 일부 키가 입력되지 않습니다.', '접수'),

(6, '파손', '캡처보드 HDMI 단자가 휘어졌습니다.', '수리중'),

(2, '고장', '아두이노 보드가 비정상적으로 과열됩니다.', '처리완료'),

(3, '고장', '부팅 과정에서 오류가 발생합니다.', '점검중'),

(5, '파손', '키보드 USB 단자가 손상되었습니다.', '접수'),

(9, '고장', '프로젝터 화면 출력이 불안정합니다.', '처리완료');


-- ============================================
-- 최종 확인
-- ============================================
SHOW TABLES;

SELECT * FROM Users;
SELECT * FROM Locker;
SELECT * FROM Equipment;
SELECT * FROM Rental;
SELECT * FROM Report;


-- ============================================
-- 신고 + 반납일시 JOIN 확인
-- 신고일시는 Rental.r_return_date 사용
-- ============================================
SELECT
    rp.report_id,
    rp.r_no,
    rp.report_type,
    rp.description,
    r.r_return_date,
    rp.status
FROM Report rp
JOIN Rental r
ON rp.r_no = r.r_no;