package model.dto;

public class RentalDTO {
    //번호 멤버변수
    private int rNo;
    private int uNo;
    private int eNo;

    // String 멤버변수 (대여일, 반납예정일, 실제반납일은  rentalTime으로 묶어서 멤버변수로 받아 사용.)
    private RentalTime rentalTime;
    private String rStatus;
    private String rCondition;


    // 기본생성자 생성
    public RentalDTO(){}

    // 매개변수와 매개인자를 받는 생성자 생성
    public RentalDTO(int rNo, int uNo, int eNo, RentalTime rentalTime, String rStatus, String rCondition){
        this.rNo = rNo;
        this.uNo = uNo;
        this.eNo = eNo;

        this.rentalTime = rentalTime;
        this.rStatus = rStatus;
        this.rCondition = rCondition;
    }

    public int getrNo() {
        return rNo;
    }

    public void setrNo(int rNo) {
        this.rNo = rNo;
    }

    public int getuNo() {
        return uNo;
    }

    public void setuNo(int uNo) {
        this.uNo = uNo;
    }

    public int geteNo() {
        return eNo;
    }

    public void seteNo(int eNo) {
        this.eNo = eNo;
    }

    public RentalTime getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(RentalTime rentalTime) {
        this.rentalTime = rentalTime;
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }

    public String getrCondition() {
        return rCondition;
    }

    public void setrCondition(String rCondition) {
        this.rCondition = rCondition;
    }




} // class end



