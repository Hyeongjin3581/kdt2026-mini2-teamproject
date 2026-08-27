package model.dto;

public class ReportDTO {

    // 1. 데이터베이스 표에서 (CRUD) 사용할 자료들을 private 멤버변수로 구성
    private int reportId;
    private int rNo;
    private String reportType;
    private String description;
    private String rReturnDate;
    private String status;

    // 2. 기본생성자, 전체매개변수생성자
    public ReportDTO() { }
    public ReportDTO( int reportId, int rNo, String reportType, String description, String rReturnDate, String status ){
        this.reportId = reportId;
        this.rNo = rNo;
        this.reportType = reportType;
        this.description = description;
        this.rReturnDate = rReturnDate;
        this.status = status;
    }

    // 3. setter and getter , toString 
    public int getReportId() {
        return reportId;
    }
    public void setReportId( int reportId ) {
        this.reportId = reportId;
    }
    public int getRNo() {
        return rNo;
    }
    public void setRNo( int rNo ) {
        this.rNo = rNo;
    }
    public String getReportType() {
        return reportType;
    }
    public void setReportType( String reportType ) {
        this.reportType = reportType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription( String description ) {
        this.description = description;
    }
    public String getrReturnDate() {
        return rReturnDate;
    }
    public void setrReturnDate( String rReturnDate ) {
        this.rReturnDate = rReturnDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus( String status ) {
        this.status = status;
    }
    
    @Override
    public String toString(){
        return "ReportDTO [ reportId=" + reportId + ", rNo =" + rNo + ", reportType =" + reportType + 
        ", description =" + description + ", rReturnDate =" + rReturnDate + ", status = " + status + "]";
    }


} // class end
