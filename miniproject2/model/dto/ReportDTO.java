package model.dto;

import java.time.LocalDateTime;

public class ReportDTO {

    // 1. 데이터베이스 표에서 (CRUD) 사용할 자료들을 private 멤버변수로 구성
    private int reportId;
    private int rNo;
    private String reportType;
    private String description;
    private LocalDateTime createdAt;
    private String status;

    // 2. 기본생성자, 전체매개변수생성자
    public ReportDTO() { }
    public ReportDTO( int reportId, int rNo, String reportType, String description, LocalDateTime createdAt, String status ){
        this.reportId = reportId;
        this.rNo = rNo;
        this.reportType = reportType;
        this.description = description;
        this.createdAt = createdAt;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt( LocalDateTime createdAt ) {
        this.createdAt = createdAt;
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
        ", description =" + description + ", createdAt =" + createdAt + ", status = " + status + "]";
    }


} // class end
