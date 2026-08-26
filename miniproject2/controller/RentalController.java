package controller;

import java.util.ArrayList;

import model.dao.RentalDAO;
import model.dto.RentalDTO;

public class RentalController {
    private RentalController(){}
    private static final RentalController instance = new RentalController();
    public static RentalController getInstance(){return instance; }
    private RentalDAO rd = RentalDAO.getInstance();


    // [1] 유효성 검사 조회
    public boolean rentalNoCheck(int r_no){
        boolean result = rd.rentalNoCheck(r_no);
        return result;
    }

    //[2] 전체 대여목록 조회
    public ArrayList<RentalDTO>rentalListPrint(){
        ArrayList<RentalDTO> result = rd.rentalListPrint();
        return result;
    }

    //[3] 대여 등록
    public boolean rentalAdd(RentalDTO rentalDTO){
        boolean result = rd.rentalAdd(rentalDTO);
        return result;
    }

    //[4] 장비반납 업데이트
    public boolean returnUpdate(RentalDTO rentalDTO){
        return rd.returnUpdate(rentalDTO);
    }
    
    //[5] 사용자 단일 대여현황 조회
    public ArrayList<RentalDTO> uRentListPrint(int u_no){
        ArrayList<RentalDTO> result = rd.uRentListPrint(u_no);
        return result;
    }

}


