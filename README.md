# 무인 장비 대여 플랫폼 (KDT 미니프로젝트 2)

학교 보관함에서 고가 장비를 무인으로 대여 · 반납하는 콘솔 프로그램을 Java MVC + JDBC + MySQL로 구현했습니다.

| 항목 | 내용 |
| --- | --- |
| 기간 | 2026.08.18 ~ 08.28 (발표 08.28) |
| 팀 | 4명 |
| 내 역할 | **대여 & 반납** 기능 (DAO · Controller · View), 관리자 화면, DB 스크립트 수정 |
| 기술 | Java · JDBC · MySQL · MVC 패턴 · Git |
| 원본 저장소 | [jihwangim09-design/miniproject2](https://github.com/jihwangim09-design/miniproject2) |

> 이 저장소는 팀 저장소의 `main` 브랜치를 커밋 기록 그대로 옮긴 사본입니다. 기록에 남아 있던 DB 접속 정보는 `***`로 가렸습니다.

## 주요 기능

- 회원가입 · 로그인 (학번 · 연락처 중복 검사)
- 장비 조회 · 대여 · 반납, 내 대여 현황
- 관리자 : 장비 · 보관함 관리, 전체 대여 목록
- 고장 · 파손 신고 (이상 반납 시 신고로 연결)

## 구조

```txt
View (StudentView · AdminView · RentalStudentView ...)
  → Controller (StudentController · AdminController)
    → DAO (RentalDAO · EquipmentDAO · LockerDAO ... , JDBC PreparedStatement)
      → MySQL (SmartLocker.sql)
```

```txt
Users ── 1 : N ──▶ Rental ◀── N : 1 ── Equipment ◀── N : 1 ── Locker
                     │
                     └── 1 : N ──▶ Report (고장 · 파손 신고)
```

## 팀원별 담당

| 팀원 | 담당 |
| --- | --- |
| 팀원 A (팀장) | 고장 & 파손 신고 |
| 팀원 B | 장비 & 보관함 |
| 팀원 C | 회원가입 & 로그인 |
| **나** | **대여 & 반납** |

## 내가 맡은 부분 : 대여 & 반납

- `rentalAdd` : 장비 상태가 '대여가능'인지 먼저 확인한 뒤 대여를 등록하고, 성공하면 장비를 '대여중'으로 바꿉니다. (중복 대여 방지)
- `returnUpdate` : 반납일(`NOW()`)과 '반납완료'를 기록합니다. 정상 반납이면 장비를 '대여가능'으로, 이상 반납이면 '점검중'으로 바꾸고 고장 신고로 연결합니다.
- `uRentListPrint` · `rentalListPrint` : 사용자별 대여 현황, 관리자 전체 대여 목록을 조회합니다.
- 관련 파일 : `miniproject2/model/dao/RentalDAO.java` (167줄 중 164줄 작성), `StudentView` · `RentalStudentView` · `RentalAdminView`

## 트러블슈팅

| 단계 | 내용 |
| --- | --- |
| 문제 | 대여 · 반납 뒤 대여 정보와 장비 상태가 서로 맞지 않아 장비가 잘못된 상태로 조회됐습니다. |
| 원인 | Rental 테이블과 Equipment 테이블의 상태 변경 로직이 분리되어 있어 두 테이블이 함께 갱신되지 않았습니다. |
| 해결 | 반납 성공 시 EquipmentDAO의 상태 변경 기능을 연계해 두 상태를 함께 갱신했습니다. |
| 결과 | 대여 시 '대여중', 정상 반납 시 '대여가능', 이상 반납 시 '점검중'으로 바뀌는 것을 확인했습니다. |

## 회고 (KPT)

- **Keep** : 서로 도움을 주고받으며 JDBC 프로젝트를 끝까지 완성했습니다.
- **Problem** : Git 병합 과정에서 충돌이 생기고, 코드 이해 부족으로 수정에 시간이 걸렸습니다.
- **Try** : 초기에 역할과 코드 규칙을 정하고, API 명세서에서 중복 기능을 미리 확인하겠습니다.

## 실행 방법

1. MySQL에서 `miniproject2/SmartLocker.sql`을 실행해 DB와 샘플 데이터를 만듭니다.
2. `miniproject2/model/dao/BaseDAO.java`의 `URL` · `USER` · `PASSWORD`를 내 DB에 맞게 바꿉니다.
3. `lib/mysql-connector-j-26.7.0.jar`를 클래스패스에 추가하고 `miniproject2/AppStart.java`를 실행합니다.
