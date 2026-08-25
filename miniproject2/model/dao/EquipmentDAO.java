package model.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import model.dto.EquipmentDTO;

public class EquipmentDAO extends BaseDAO {
    private static final EquipmentDAO instance = new EquipmentDAO();
    public static EquipmentDAO getInstance( ) {return instance;}
    private ArrayList<Object> equipmentList = new ArrayList<>();

    // 장비 등록
    @Override 
    public boolean save(Object obj) {
            
            EquipmentDTO equipmentDTO = (EquipmentDTO) obj;
            try{String sql = "INSERT INTO equipment (e_Name, e_Category, e_Status, l_NO) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, equipmentDTO.getE_Name());
                ps.setString(2, equipmentDTO.getE_Category());
                ps.setString(3, equipmentDTO.getE_Status());
                ps.setInt(4, equipmentDTO.getL_NO());
                int result = ps.executeUpdate();

            if (result == 1) { return true; }       
    }catch(Exception e){ System.out.println("--> 장비 등록 실패" + e);}
    return false;
}

    // 전체 장비 조회
  public ArrayList<Object> findAll() {
    ArrayList<Object> list = new ArrayList<>();
            try {String sql = "SELECT * FROM equipment";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                EquipmentDTO equipmentDTO = new EquipmentDTO(
                rs.getInt("e_No"),
                rs.getString("e_Name"),
                rs.getString("e_Category"),
                rs.getString("e_Status"),
                rs.getInt("l_NO")
                );
                list.add(equipmentDTO);
            }
        }catch (Exception e) {System.out.println("장비조회 실패" + e);}
        return list;    
    } // class end
}




