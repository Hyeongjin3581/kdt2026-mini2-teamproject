package controller;

import java.util.ArrayList;

import model.dao.EquipmentDAO;
import model.dao.LockerDAO;
import model.dto.EquipmentDTO;

public class StudentController {
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

// 5. 특정 보관함 장비 조회
public EquipmentDTO l_equipmentfind(int l_No) {
    return lockerDAO.l_equipmentfind(l_No);
}



}
