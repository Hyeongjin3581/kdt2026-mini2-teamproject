package model.dto;

public class UsersDTO {
    private int u_no;
    private String u_pwd;
    private String u_phone;
    private String u_name;
    private String u_grade;
    private String u_student_id;
    
    public UsersDTO(int u_no, String u_pwd, String u_phone, String u_name, String u_grade, String u_student_id) {
        this.u_no = u_no;
        this.u_pwd = u_pwd;
        this.u_phone = u_phone;
        this.u_name = u_name;
        this.u_grade = u_grade;
        this.u_student_id = u_student_id;
    }

    public int getU_no() {
        return u_no;
    }

    public void setU_no(int u_no) {
        this.u_no = u_no;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_grade() {
        return u_grade;
    }

    public void setU_grade(String u_grade) {
        this.u_grade = u_grade;
    }

    public String getU_student_id() {
        return u_student_id;
    }

    public void setU_student_id(String u_student_id) {
        this.u_student_id = u_student_id;
    }

    @Override
    public String toString() {
        return "UsersDTO [u_no=" + u_no + ", u_pwd=" + u_pwd + ", u_phone=" + u_phone + ", u_name=" + u_name
                + ", u_grade=" + u_grade + ", u_student_id=" + u_student_id + "]";
    }

}