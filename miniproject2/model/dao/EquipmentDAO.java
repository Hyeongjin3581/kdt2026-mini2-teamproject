package model.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.dto.EquipmentDTO;

public class EquipmentDAO extends BaseDAO {
    private EquipmentDAO() { }
    private static final EquipmentDAO instance = new EquipmentDAO();
    public static EquipmentDAO getInstance( ) {return instance;}


        // 1. 장비 유효성 검사
    public boolean e_NoCheck(int e_No) {
            String sql = "SELECT e_no FROM equipment WHERE e_no = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, e_No);
            try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return true;
                }
            }
        } catch (Exception e) {
            System.out.println("장비 유효성 검사 실패 : " + e);
        }
        return false;
    }

        // 2. 전체 장비 조회 (전체 장비 목록 보기)
    public ArrayList<Object> e_findAll() {
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
        } 


     // 3. 장비 상세 조회 (장비 검색)
    public EquipmentDTO e_find(int e_No) {
            try {String sql = "SELECT * FROM equipment WHERE e_no = ?"; 
                PreparedStatement pstmt = conn.prepareStatement(sql); {
                pstmt.setInt(1, e_No);
                try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                return new EquipmentDTO(
                    rs.getInt("e_no"),
                    rs.getString("e_name"),
                    rs.getString("e_category"),
                    rs.getString("e_status"),
                    rs.getInt("l_no")
                    );
                }
            }
        }
            }catch (Exception e) {System.out.println("장비 상세 조회 실패 : " + e);}
            return null;
        }

    // 4. 장비 카테고리별 조회
    public ArrayList<EquipmentDTO> e_categoryfind(String e_Category) {  
            ArrayList<EquipmentDTO> list = new ArrayList<>();
            String sql = "SELECT * FROM equipment WHERE e_category = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, e_Category);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                    EquipmentDTO equipmentDTO = new EquipmentDTO(
                        rs.getInt("e_no"),
                        rs.getString("e_name"),
                        rs.getString("e_category"),
                        rs.getString("e_status"),
                        rs.getInt("l_no")
                    );
                    list.add(equipmentDTO);
                }
            }

        } catch (Exception e) {
            System.out.println("카테고리별 장비 조회 실패 : " + e);
        }
        return list;
    }

        // 5. 대여가능 장비 조회 (대여 조회)
    public ArrayList<EquipmentDTO> e_available() {
            ArrayList<EquipmentDTO> list = new ArrayList<>(); 
            try {String sql = "SELECT * FROM equipment WHERE e_status = '대여가능'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(); {
            while (rs.next()) {
                EquipmentDTO dto = new EquipmentDTO(
                    rs.getInt("e_no"),
                    rs.getString("e_name"),
                    rs.getString("e_category"),
                    rs.getString("e_status"),
                    rs.getInt("l_no")
                    );
                list.add(dto);
            }
        } 
            }catch (Exception e) {System.out.println("대여가능 장비 조회 실패 : " + e);}
        return list;
    }

    // 6. 장비 등록
    public boolean e_add(EquipmentDTO equipmentDTO) {
        String sql = "INSERT INTO equipment (e_name, e_category, e_status, l_no) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipmentDTO.getE_Name());
            pstmt.setString(2, equipmentDTO.getE_Category());
            pstmt.setString(3, equipmentDTO.getE_Status());
            pstmt.setInt(4, equipmentDTO.getL_NO());
            
            int result = pstmt.executeUpdate();
            return result == 1;
            } catch (Exception e) {System.out.println("장비 등록 실패 : " + e);}
        return false;
    }

    // 7. 장비 정보 수정
    public boolean e_update(EquipmentDTO equipmentDTO) {
        String sql = "UPDATE equipmentSET e_name = ?,e_category = ?,e_status = ?,l_no = ?WHERE e_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipmentDTO.getE_Name());
            pstmt.setString(2, equipmentDTO.getE_Category());
            pstmt.setString(3, equipmentDTO.getE_Status());
            pstmt.setInt(4, equipmentDTO.getL_NO());
            pstmt.setInt(5, equipmentDTO.getE_No());

            int result = pstmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {System.out.println("장비 정보 수정 실패 : " + e);}
        return false;
    }

    // 8. 장비 상태 변경
    public boolean e_statusupdate(int e_No, String e_Status) {
        String sql ="UPDATE equipment SET e_status = ? WHERE e_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, e_Status);
            pstmt.setInt(2, e_No);
            
            int result = pstmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {System.out.println("장비 상태 변경 실패 : " + e);}
        return false;
    }


     // 9. 장비 보관함 변경
    public boolean e_lockerupdate(int e_No, int l_No) {
        String sql = "UPDATE equipment SET l_no = ? WHERE e_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, l_No);
            pstmt.setInt(2, e_No);

            int result = pstmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            System.out.println("장비 보관함 변경 실패 : " + e);
        }
        return false;       
    }


    // 10. 장비 삭제
    public boolean e_delete(int e_No) {
        String sql = "DELETE FROM equipment WHERE e_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, e_No);
            int result = pstmt.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            System.out.println("장비 삭제 실패 : " + e);
        }
        return false;
    }

} // class end






