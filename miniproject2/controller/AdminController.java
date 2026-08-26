package controller;

import java.util.ArrayList;

import model.dao.ReportDAO;
import model.dto.ReportDTO;
import model.dto.RentalDTO;

public class AdminController {
    // ReportDAO 객체 가져오기
    private ReportDAO reportDAO = ReportDAO.getInstance();


// [2] 전체 신고내역 조회
public ArrayList<ReportDTO> reportFindAll() {
    return reportDAO.report_findAll();
}


// [3] 신고 상세 조회
public ReportDTO reportFind(int reportId) {
    return reportDAO.report_find(reportId);
}


// [4] 신고 유형별 조회
public ArrayList<ReportDTO> reportTypeFind(String reportType) {
    return reportDAO.report_typeFind(reportType);
}


// [5] 처리 상태별 조회
public ArrayList<ReportDTO> reportStatusFind(String status) {
    return reportDAO.report_statusFind(status);
}


// [6] 신고 처리상태 변경
public boolean reportStatusUpdate(int reportId, String status) {
    return reportDAO.report_statusUpdate(reportId, status);
}


// [7] 장비 최근 이용내역 조회
public RentalDTO recentRentalFind(int eNo) {
    return reportDAO.recentRental_find(eNo);
}
}
