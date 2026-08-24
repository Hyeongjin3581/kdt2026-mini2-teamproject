package model.dao;
import java.util.ArrayList;

public class EquipmentDAO  {
    private static final EquipmentDAO instance = new EquipmentDAO();
    public static EquipmentDAO getInstance( ) { return instance;}
    private ArrayList<Object> equipmentList = new ArrayList<>();

    @Override
    public boolean save(Object obj) {
            equipmentList.add(obj);
            return true;
    }
} // class end
