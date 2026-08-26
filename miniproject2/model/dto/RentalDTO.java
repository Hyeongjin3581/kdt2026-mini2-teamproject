package model.dto;

public class RentalDTO {
    //번호 멤버변수
    private int r_no;
    private int u_no;
    private int e_no;
    private String r_date;
    private String r_due_date;
    private String r_return_date;
    private String r_status;
    private String r_condition;


    // 기본생성자 생성
    public RentalDTO(){}

    // 매개변수와 매개인자를 받는 생성자 생성
    public RentalDTO(int r_no, int u_no, int e_no,String r_date,String r_due_date,String r_return_date , String r_status, String r_condition){
        this.r_no = r_no;
        this.u_no = u_no;
        this.e_no = e_no;
        this.r_date = r_date;
        this.r_due_date = r_due_date;
        this.r_return_date = r_return_date;
        this.r_status = r_status;
        this.r_condition = r_condition;
    }

    public int getR_no() {
        return r_no;
    }

    public void setR_no(int r_no) {
        this.r_no = r_no;
    }

    public int getU_no() {
        return u_no;
    }

    public void setU_no(int u_no) {
        this.u_no = u_no;
    }

    public int getE_no() {
        return e_no;
    }

    public void setE_no(int e_no) {
        this.e_no = e_no;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public String getR_due_date() {
        return r_due_date;
    }

    public void setR_due_date(String r_due_date) {
        this.r_due_date = r_due_date;
    }

    public String getR_return_date() {
        return r_return_date;
    }

    public void setR_return_date(String r_return_date) {
        this.r_return_date = r_return_date;
    }

    public String getR_status() {
        return r_status;
    }

    public void setR_status(String r_status) {
        this.r_status = r_status;
    }

    public String getR_condition() {
        return r_condition;
    }

    public void setR_condition(String r_condition) {
        this.r_condition = r_condition;
    }

    @Override
    public String toString() {
        return "RentalDTO [r_no=" + r_no + ", u_no=" + u_no + ", e_no=" + e_no + ", r_date=" + r_date + ", r_due_date="
                + r_due_date + ", r_return_date=" + r_return_date + ", r_status=" + r_status + ", r_condition="
                + r_condition + "]";
    }


} // class end


