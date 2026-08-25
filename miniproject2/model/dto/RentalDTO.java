package model.dto;

public class RentalDTO {
    //번호 멤버변수
    private int rNo;
    private int uNo;
    private int eNo;
    private String rdate;
    private String rduedate;
    private String rreturndate;
    private String rStatus;
    private String rCondition;


    // 기본생성자 생성
    public RentalDTO(){}

    // 매개변수와 매개인자를 받는 생성자 생성
    public RentalDTO(int rNo, int uNo, int eNo,String rdate,String rduedate,String rreturndate , String rStatus, String rCondition){
        this.rNo = rNo;
        this.uNo = uNo;
        this.eNo = eNo;
        this.rdate = rdate;
        this.rduedate = rduedate;
        this.rreturndate = rreturndate;
        this.rStatus = rStatus;
        this.rCondition = rCondition;
    }

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

    @Override
    public String toString() {
        return "RentalDTO [rNo=" + rNo + ", uNo=" + uNo + ", eNo=" + eNo + ", rdate=" + rdate + ", rduedate=" + rduedate
                + ", rreturndate=" + rreturndate + ", rStatus=" + rStatus + ", rCondition=" + rCondition + "]";
    }

} // class end



