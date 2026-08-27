package controller;

import java.util.ArrayList;

import model.dao.EquipmentDAO;
import model.dao.LockerDAO;
import model.dto.EquipmentDTO;
import model.dto.LockerDTO;
import model.dao.RentalDAO;
import model.dto.RentalDTO;

public class StudentController {
    private static final StudentController instance = new StudentController();
    public static StudentController getInstance(){return instance;}

    private RentalDAO rd = RentalDAO.getInstance();

    private EquipmentDAO equipmentDAO = EquipmentDAO.getInstance();
    private LockerDAO lockerDAO = LockerDAO.getInstance();

    // 장비 조회

    // 1. 전체 장비 조회
    public ArrayList<Object> e_findAll() {
        return equipmentDAO.e_findAll();
    }
    // 2. 장비 상세 조회
    public EquipmentDTO e_find(int e_No) {
        return equipmentDAO.e_find(e_No);
    }
    // 3. 장비 카테고리별 조회
    public ArrayList<EquipmentDTO> e_categoryfind(String e_Category) {
        return equipmentDAO.e_categoryfind(e_Category);
    }
    // 4. 대여가능 장비 조회
    public ArrayList<EquipmentDTO> e_available() {
        return equipmentDAO.e_available();
    }


    // 보관함 조회

    // 1. 전체 보관함 조회
    public ArrayList<Object> l_findAll() {
        return lockerDAO.l_findAll();
    }


    // 2. 보관함 상세 조회
    public LockerDTO l_find(int l_No) {
        return lockerDAO.l_find(l_No);
    }


    // 3. 특정 보관함 장비 조회
    public EquipmentDTO l_equipmentfind(int l_No) {
        return lockerDAO.l_equipmentfind(l_No);
    }

    // 대여&반납
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
