package controller;

import java.util.ArrayList;

import model.dao.EquipmentDAO;
import model.dao.LockerDAO;
import model.dto.EquipmentDTO;
import model.dto.LockerDTO;

public class AdminController {
private EquipmentDAO equipmentDAO = EquipmentDAO.getInstance();
private LockerDAO lockerDAO = LockerDAO.getInstance();
// 장비

// 1. 장비번호 유효성 검사
public boolean e_NoCheck(int e_No) {
    return equipmentDAO.e_NoCheck(e_No);
}

// 2. 전체 장비 조회
public ArrayList<Object> e_findAll() {
    return equipmentDAO.e_findAll();
}

// 3. 장비 상세 조회
public EquipmentDTO e_find(int e_No) {
    return equipmentDAO.e_find(e_No);
}

// 6. 장비 등록
public boolean e_add(EquipmentDTO equipmentDTO) {
    return equipmentDAO.e_add(equipmentDTO);
}

// 7. 장비 정보 수정
public boolean e_update(EquipmentDTO equipmentDTO) {
    return equipmentDAO.e_update(equipmentDTO);
}

// 8. 장비 상태 변경
public boolean e_statusupdate(int e_No, String e_Status) {
    return equipmentDAO.e_statusupdate(e_No, e_Status);
}

// 9. 장비 보관함 변경
public boolean e_lockerupdate(int e_No, int l_No) {
    return equipmentDAO.e_lockerupdate(e_No, l_No);
}

// 10. 장비 삭제
public boolean e_delete(int e_No) {
    return equipmentDAO.e_delete(e_No);
}
/*------------------------------------------------------------------*/
// 보관함

// 1. 보관함 번호 유효성 검사
public boolean l_NoCheck(int l_No) {
    return lockerDAO.l_NoCheck(l_No);
}

// 2. 보관함 등록
public boolean l_add(LockerDTO lockerDTO) {
    return lockerDAO.i_add(lockerDTO);
}

// 3. 전체 보관함 조회
public ArrayList<Object> l_findAll() {
    return lockerDAO.l_findAll();
}

// 4. 보관함 개별 조회
public LockerDTO l_findByNo(int l_No) {
    return lockerDAO.findByNo(l_No);
}

// 5. 보관함 수정
public boolean l_update(LockerDTO lockerDTO) {
    return lockerDAO.update(lockerDTO);
}

// 6. 보관함 삭제
public boolean l_delete(int l_No) {
    return lockerDAO.delete(l_No);
}

// 7. 보관함 상태 변경
public boolean l_statusupdate(int l_No, String l_Status) {
    return lockerDAO.l_statusupdate(l_No, l_Status);
}

// 8. 보관함 장비 조회
public EquipmentDTO l_equipmentfind(int l_No) {
    return lockerDAO.l_equipmentfind(l_No);
}
}
