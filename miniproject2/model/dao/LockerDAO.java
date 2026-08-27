package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.dto.EquipmentDTO;
import model.dto.LockerDTO;

public class LockerDAO extends BaseDAO {

    // 싱글톤
    private LockerDAO() {}
    private static final LockerDAO instance = new LockerDAO();
    public static LockerDAO getInstance() {return instance;}


    // 1. 보관함 번호 유효성 검사
    public boolean l_NoCheck(int l_No) {
        String sql = "SELECT l_No FROM locker WHERE l_No = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, l_No);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("보관함 번호 검사 실패 : " + e);
        }
        return false;
    }


    // 2. 보관함 등록
    public boolean l_add(Object obj) {
        LockerDTO lockerDTO = (LockerDTO) obj;
        String sql = "INSERT INTO locker(l_Location, l_Status)VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, lockerDTO.getL_Location());
            pstmt.setString(2, lockerDTO.getL_Status());
            int result = pstmt.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("보관함 등록 실패 : " + e);
        }
        return false;
    }


    // 3. 전체 보관함 조회
        public ArrayList<Object> l_findAll() {
        ArrayList<Object> list = new ArrayList<>();
        String sql = "SELECT l_No, l_Location, l_StatusFROM lockerORDER BY l_No";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                int l_No = rs.getInt("l_No");
                String l_Location = rs.getString("l_Location");
                String l_Status = rs.getString("l_Status");
                LockerDTO lockerDTO = new LockerDTO(l_No, l_Location, l_Status);
                list.add(lockerDTO);
            }
        } catch (Exception e) {
            System.out.println("전체 보관함 조회 실패 : " + e);
        }
        return list;
    }

    // 4. 보관함 개별 조회
        public LockerDTO l_find(int l_No) {
        String sql = "SELECT l_No, l_Location, l_StatusFROM lockerWHERE l_No = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, l_No);
                try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                return new LockerDTO(
                rs.getInt("l_No"),
                rs.getString("l_Location"),
                rs.getString("l_Status")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("보관함 개별 조회 실패 : " + e);
        }
        return null;
    }


    // 5. 보관함 수정
        public boolean l_update(LockerDTO lockerDTO) {
        String sql = "UPDATE lockerSET l_Location = ?,l_Status = ? WHERE l_No = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, lockerDTO.getL_Location());
                pstmt.setString(2, lockerDTO.getL_Status());
                pstmt.setInt(3, lockerDTO.getL_No());
                int result = pstmt.executeUpdate();
                if (result == 1) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("보관함 수정 실패 : " + e);
            }
            return false;
        }


    // 6. 보관함 삭제
    public boolean l_delete(int l_No) {
        String sql = "DELETE FROM locker WHERE l_No = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, l_No);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("보관함 삭제 실패 : " + e);
        }

        return false;
    }

    // 7. 보관함 상태 변경
        public boolean l_statusupdate(int l_No, String l_Status) {
            String sql = "UPDATE locker SET l_Status = ? WHERE l_No = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, l_Status);
            pstmt.setInt(2, l_No);
            int result = pstmt.executeUpdate();
            return result == 1;
            } catch (Exception e) {
                System.out.println("보관함 상태 변경 실패 : " + e);
            }
            return false;
        }

// 8. 보관함 장비 조회
        public EquipmentDTO l_equipmentfind(int l_No) {
            String sql = "SELECT * FROM equipment WHERE l_No = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, l_No);
            try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
            return new EquipmentDTO(
            rs.getInt("e_No"),
            rs.getString("e_Name"),
            rs.getString("e_Category"),
            rs.getString("e_Status"),
            rs.getInt("l_No")
                );
                    }
                }
            } catch (Exception e) {
                System.out.println("보관함 장비 조회 실패 : " + e);
            }
            return null;
        }
}