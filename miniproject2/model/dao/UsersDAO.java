package model.dao;

import java.sql.*;
import java.util.ArrayList;

import model.dto.UsersDTO;

// 싱글톤 패턴
public class UsersDAO extends BaseDAO {
    private UsersDAO (){}; 
    private static final UsersDAO instance = new UsersDAO();
    public static UsersDAO getInstance() { return instance; }

    // 회원가입
    public boolean signup( UsersDTO dto ) { 
        boolean result = false; // 기본값 false
        try{
            // 회원 정보 추가
            String sql = "insert into Users ( u_student_id, u_pwd, u_name, u_phone) values (?, ?, ?, ?)";
            // PreparedStatement 객체 생성
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, dto.getU_student_id());
            ps.setString(2, dto.getU_pwd());
            ps.setString(3, dto.getU_name());
            ps.setString(4, dto.getU_phone());
            int rows = ps.executeUpdate(); // DB에 들어간 줄(row) 수 확인 성공하면 1 실패시 0
            result = (rows == 1); // 위에서 제대로 실행 됐으면 true 아닐시 false 

            ps.close();

        }catch( Exception e ) { e.printStackTrace(); } // 중간에 에러 발생시 에러 내용 콘솔에 출력
        return result; // try end
    } // signup end

    // 로그인
    public UsersDTO login(String studentId, String pwd) {
    UsersDTO loginUser = null;
    try{
        String sql = "SELECT * FROM Users WHERE u_student_id = ? AND u_pwd = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, studentId);
        ps.setString(2, pwd);
        ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                loginUser = new UsersDTO(
                rs.getInt("u_no"),
                rs.getString("u_student_id"),
                rs.getString("u_pwd"),
                rs.getString("u_name"),
                rs.getString("u_phone"),
                rs.getString("u_grade")
            );
        }
            rs.close();
            ps.close();
        }catch( Exception e ) { e.printStackTrace(); }
        return loginUser;
    }   

    // 학번 유효성 검사
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

    // 연락처 유효성 검사
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

    // 전체 회원 조회 (관리자용)
    public ArrayList<UsersDTO> selectAllUsers() {
        ArrayList<UsersDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                UsersDTO user = new UsersDTO(
                    rs.getInt("u_no"),
                    rs.getString("u_student_id"),
                    rs.getString("u_pwd"),
                    rs.getString("u_name"),
                    rs.getString("u_phone"),
                    rs.getString("u_grade")
                );
                list.add(user);
            }
            rs.close();
            ps.close();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return list;
    }

} // class end