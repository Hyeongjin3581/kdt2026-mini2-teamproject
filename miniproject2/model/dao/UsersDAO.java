package model.dao;

import java.sql.*;
import model.dto.UsersDTO;

public class UsersDAO implements BaseDAO {
    private UsersDAO (){}; 
    private static final UsersDAO instance = new UsersDAO();
    public static UsersDAO getInstance() { return instance; }


    public boolean signup( UsersDTO dto ) {
        boolean result = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(null, null, null);
            String sql = "insert into Users (u_id, u_pwd, u_phone, u_name, u_student_id) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getU_id());
            ps.setString(2, dto.getU_pwd());
            ps.setString(3, dto.getU_phone());
            ps.setString(4, dto.getU_name());
            ps.setString(5, dto.getU_student_id());
            int rows = ps.executeUpdate();
            result = (rows == 1);
        }catch( Exception e ) { e.printStackTrace(); } 
        return result; // try end
    } // signup end
} // class end
