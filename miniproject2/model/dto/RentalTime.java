package model.dto;

public class RentalTime {
    private String rdate;
    private String rduedate;
    private String rreturndate;


    // 해당 멤버변수들은 하나의 클래스 안에서 3개의 필드를 모두 두고 관리하는 것이 객체지향적 설계관점에서 표준적.
    // 왜냐하면 하나의 대여 데이터(행)을 표현하는 객체이기 때문에, & 시간 흐름에 따른 상태 변화를 수용
    // DB의 한 행을 SELECT * FROM rental WHERE r_no = 1;로 조회해 오면, 결과 집합(ResultSet) 1개 행 안에서 r_date, r_due_date, r_return_date가 한꺼번에 뽑혀 나옴.

    // 기본생성자 생성
    public RentalTime(){}

    // DB에서 한 행에서 조회해오기 때문에,  3개의 매개인자를 한번에 받는 생성자를 생성한다.
    public RentalTime(String rdate ,String rduedate,String rreturndate){
        this.rdate = rdate;
        this.rduedate = rduedate;
        this.rreturndate = rreturndate;
   }

   // getter와 setter 생성.
    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getRduedate() {
        return rduedate;
    }

    public void setRduedate(String rduedate) {
        this.rduedate = rduedate;
    }

    public String getRreturndate() {
        return rreturndate;
    }

    public void setRreturndate(String rreturndate) {
        this.rreturndate = rreturndate;
    }

} // class end
