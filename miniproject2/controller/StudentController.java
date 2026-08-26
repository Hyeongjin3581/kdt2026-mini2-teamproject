package controller;

import java.util.ArrayList;

import model.dao.RentalDAO;
import model.dto.RentalDTO;

public class StudentController {
    private StudentController(){} // 기본 생성자 생성
    private static final StudentController instance = new StudentController();
    public static StudentController getInstance(){return instance;}

    private RentalDAO rd = RentalDAO.getInstance();
    /* 해당 부분에 DAO 싱글톤 생성해주면 됨. */

    // [1] 유효성 검사 조회
    public boolean rentalNoCheck(int r_no){
        boolean result = rd.rentalNoCheck(r_no);
        return result;
    }

    //[3] 대여 등록 (사용자)
    public boolean rentalAdd(RentalDTO rentalDTO){
        boolean result = rd.rentalAdd(rentalDTO);
        return result;
    }

    //[4] 장비반납 업데이트 (사용자)
    public boolean returnUpdate(RentalDTO rentalDTO){
        return rd.returnUpdate(rentalDTO);
    }

    //[5] 사용자 단일 대여현황 조회 (관리자 & 사용자)
    public ArrayList<RentalDTO> uRentListPrint(int u_no){
        ArrayList<RentalDTO> result = rd.uRentListPrint(u_no);
        return result;
    }
}
