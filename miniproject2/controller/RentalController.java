package controller;

import java.util.ArrayList;

import model.dao.EquipmentDAO;
import model.dao.LockerDAO;;

public class RentalController {
private EquipmentDAO equipmentDAO = EquipmentDAO.getInstance();
private LockerDAO lockerDAO = LockerDAO.getInstance();


// 대여/반납에 따른 장비와 보관함 상태 처리

// 장비 상태 변경
public boolean e_statusupdate(int e_No, String e_Status) {
    return equipmentDAO.e_statusupdate(e_No, e_Status);
}

// 장비 보관함 변경
public boolean e_lockerupdate(int e_No, int l_No) {
    return equipmentDAO.e_lockerupdate(e_No, l_No);
}

// 보관함 상태 변경
public boolean l_statusupdate(int l_No, String l_Status) {
    return lockerDAO.l_statusupdate(l_No, l_Status);
}
}
