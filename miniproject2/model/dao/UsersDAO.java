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
            String sql = "insert into Users ( u_pwd, u_phone, u_name, u_student_id) values (?, ?, ?, ?)";
            // PreparedStatement 객체 생성
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, dto.getU_pwd());
            ps.setString(2, dto.getU_phone());
            ps.setString(3, dto.getU_name());
            ps.setString(4, dto.getU_student_id());
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

    // 회원 상세 조회
    public UsersDTO getUserInfo( int u_no ) {
        UsersDTO user = null;
        try{
            String sql = "SELECT * FROM Users WHERE u_no = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, u_no);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UsersDTO(
                    rs.getInt("u_no"),
                    rs.getString("u_pwd"),
                    rs.getString("u_phone"),
                    rs.getString("u_name"),
                    rs.getString("u_grade"),
                    rs.getString("u_student_id")
                );
            }
            rs.close();
            ps.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return user;
    }

    // 전체회원조회(관리자용)
    public ArrayList<UsersDTO> selectAllUsers() {
        ArrayList<UsersDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                UsersDTO user = new UsersDTO(
                    rs.getInt("u_no"),
                    rs.getString("u_pwd"),
                    rs.getString("u_phone"),
                    rs.getString("u_name"),
                    rs.getString("u_grade"),
                    rs.getString("u_student_id")
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
