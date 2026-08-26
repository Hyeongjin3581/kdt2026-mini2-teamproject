package controller;

    import model.dao.ReportDAO;
    import model.dto.ReportDTO;

public class StudentController {
    // ReportDAO 객체 가져오기
    private ReportDAO reportDAO = ReportDAO.getInstance();


// [1] 고장/파손 신고 등록
    public boolean reportAdd(ReportDTO reportDTO) {
        return reportDAO.report_add(reportDTO);
}

}
