package controller;

import java.util.ArrayList;

import model.dao.RentalDAO;
import model.dto.RentalDTO;

public class AdminController {
    private AdminController(){} 
    private static final AdminController instance = new AdminController(); 
    public static AdminController getInstance(){return instance;}

    private RentalDAO rd = RentalDAO.getInstance(); 
    /* 해당 부분에 DAO 싱글톤 생성해주면 됨. */
    


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

} // class end
