package model.dao;

import java.sql.*;
import model.dto.UsersDTO;

public class UsersDAO extends BaseDAO {
    private UsersDAO (){}; 
    private static final UsersDAO instance = new UsersDAO();
    public static UsersDAO getInstance() { return instance; }


    public boolean signup( UsersDTO dto ) {
        boolean result = false; // 기본값 false
        try{
            String sql = "insert into Users (u_id, u_pwd, u_phone, u_name, u_student_id) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, dto.getU_id());
            ps.setString(2, dto.getU_pwd());
            ps.setString(3, dto.getU_phone());
            ps.setString(4, dto.getU_name());
            ps.setString(5, dto.getU_student_id());
            int rows = ps.executeUpdate(); // DB에 들어간 줄(row) 수 확인 
            result = (rows == 1); // 1줄이 들어갔으면 true 아니면 false 

            ps.close();

        }catch( Exception e ) { e.printStackTrace(); } // 에러 추적
        return result; // try end
    } // signup end

    public UsersDTO login( UsersDTO dto ) {
        UsersDTO loginUser = null; // 기본값 null
        try{
            String sql = "SELECT * FROM Users WHERE u_student_id = ? AND u_pwd = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(5, dto.getU_student_id());
            ps.setString(5, dto.getU_student_id());

            ResultSet rs = ps.executeQuery(); // insert는 update지만 select는 query를 사용

            if(rs.next()) {
                loginUser = new UsersDTO(
                    rs.getInt("u_no"),
                    rs.getString("u_id"),
                    rs.getString("u_pwd"),
                    rs.getString("u_phone"),
                    rs.getString("u_name"),
                    rs.getString("u_grade"),
                    rs.getString("u_student_id")
                );
            }

            rs.close();
            ps.close();

        }catch( Exception e ) { e.printStackTrace(); }
        return loginUser; // 성공하면 유저 정보 실패하면 null
    }

    public boolean checkStudentId( String studentId ) {
        boolean result = false;
        try{
            String sql = "SELECT * FROM Users WHERE u_student_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                result = true;
            }

            rs.close();
            ps.close();
        } catch ( Exception e ) { e.printStackTrace(); }
        return result;
    }

    public boolean checkPhone( String phone ) {
        boolean result = true;
        try{
            String sql = "SELECT * FROM Users WHERE u_phone = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                result = false;
            }

            rs.close();
            ps.close();
        } catch ( Exception e ) { e.printStackTrace(); }
        return result;
    }

    public boolean checkID( String id ) {
        boolean result = false;
        try{
            String sql = "SELECT * FROM Users WHERE u_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                result = true;
            }

            rs.close();
            ps.close();
        } catch ( Exception e ) { e.printStackTrace(); }
        return result;
    }
} // class end
