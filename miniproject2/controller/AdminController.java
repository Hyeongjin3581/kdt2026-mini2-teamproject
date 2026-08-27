package controller;

import java.util.ArrayList;

import model.dao.RentalDAO;
import model.dao.ReportDAO;
import model.dto.RentalDTO;
import model.dto.ReportDTO;


public class AdminController {
    // ReportDAO 객체 가져오기
    private AdminController(){} 
    private static final AdminController instance = new AdminController(); 
    public static AdminController getInstance(){return instance;}

    private RentalDAO rd = RentalDAO.getInstance(); 
    private ReportDAO reportDAO = ReportDAO.getInstance();


    // [1] 전체 신고내역 조회
    public ArrayList<ReportDTO> reportFindAll() {
        return reportDAO.report_findAll();
    }


    // [2] 신고 상세 조회
    public ReportDTO reportFind(int reportId) {
        return reportDAO.report_find(reportId);
    }


    // [3] 신고 유형별 조회
    public ArrayList<ReportDTO> reportTypeFind(String reportType) {
        return reportDAO.report_typeFind(reportType);
    }


    // [4] 처리 상태별 조회
    public ArrayList<ReportDTO> reportStatusFind(String status) {
        return reportDAO.report_statusFind(status);
    }


    // [5] 신고 처리상태 변경
    public boolean reportStatusUpdate(int reportId, String status) {
        return reportDAO.report_statusUpdate(reportId, status);
    }


    // [6] 장비 최근 이용내역 조회
    public RentalDTO recentRentalFind(int eNo) {
        return reportDAO.recentRental_find(eNo);
    }


    // RentalDAO [1] 유효성 검사 조회
    public boolean rentalNoCheck(int r_no){
        boolean result = rd.rentalNoCheck(r_no);
        return result;
    }

    //RentalDAO [2] 전체 대여목록 조회 (관리자)
    public ArrayList<RentalDTO>rentalListPrint(){
        ArrayList<RentalDTO> result = rd.rentalListPrint();
        return result;
    }

    //RentalDAO [5] 사용자 단일 대여현황 조회 (관리자 & 사용자)
    public ArrayList<RentalDTO> uRentListPrint(int u_no){
        ArrayList<RentalDTO> result = rd.uRentListPrint(u_no);
        return result;
    }

}
