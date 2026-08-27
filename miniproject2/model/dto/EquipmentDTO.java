package model.dto;

public class EquipmentDTO {
    // 1. 필드 선언
    private int e_No;
    private String e_Name;
    private String e_Category;
    private String e_Status;
    private int l_NO;

    
    public EquipmentDTO() {}
    // 3. 전체 매개변수 생성자 (조회 시 사용)
    public EquipmentDTO(int e_No, String e_Name, String e_Category, String e_Status, int l_NO) {
        this.e_No = e_No;
        this.e_Name = e_Name;
        this.e_Category = e_Category;
        this.e_Status = e_Status;
        this.l_NO = l_NO;
    }

    // 4. 신규 등록용 생성자 
    public EquipmentDTO(String e_Name, String e_Category, String e_Status, int l_NO) {
        this.e_Name = e_Name;
        this.e_Category = e_Category;
        this.e_Status = e_Status;
        this.l_NO = l_NO;
    }

    // 5. Getter Setter
    public int getE_No() {
        return e_No;
    }

    public void setE_No(int e_No) {
        this.e_No = e_No;
    }

    public String getE_Name() {
        return e_Name;
    }

    public void setE_Name(String e_Name) {
        this.e_Name = e_Name;
    }

    public String getE_Category() {
        return e_Category;
    }

    public void setE_Category(String e_Category) {
        this.e_Category = e_Category;
    }

    public String getE_Status() {
        return e_Status;
    }

    public void setE_Status(String e_Status) {
        this.e_Status = e_Status;
    }

    public int getL_NO() {
        return l_NO;
    }

    public void setL_NO(int l_NO) {
        this.l_NO = l_NO;
    }

   
    @Override
    public String toString() {
        return "EquipmentDTO [eNo=" + e_No + ", eName=" + e_Name + ", eCategory=" + e_Category 
                + ", eStatus=" + e_Status + ", lNo=" + l_NO + "]";
    } // main end
} // class end