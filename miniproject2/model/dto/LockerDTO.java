package model.dto;

public class LockerDTO {
    // 1. 필드 선언
    private int l_No;          
    private String l_Location;   
    private String l_Status;      

    public LockerDTO() {}
    // 3. 전체 매개변수 생성자 (조회 시 사용)
    public LockerDTO(int l_No, String l_Location, String l_Status) {
        this.l_No = l_No;
        this.l_Location = l_Location;
        this.l_Status = l_Status;
    }

    // 4. 신규 등록용 생성자 
    public LockerDTO(String l_Location, String l_Status) {
        this.l_Location = l_Location;
        this.l_Status = l_Status;
    }


    // 5. Getter / Setter
    public int getL_No() {
        return l_No;
    }

    public void setL_No(int l_No) {
        this.l_No = l_No;
    }

    public String getL_Location() {
        return l_Location;
    }

    public void setL_Location(String l_Location) {
        this.l_Location = l_Location;
    }

    public String getL_Status() {
        return l_Status;
    }

    public void setL_Status(String l_Status) {
        this.l_Status = l_Status;
    }


    // 6. toString
    @Override
    public String toString() {
        return "LockerDTO [l_No=" + l_No
                + ", l_Location=" + l_Location
                + ", l_Status=" + l_Status + "]";
    }

} // class end