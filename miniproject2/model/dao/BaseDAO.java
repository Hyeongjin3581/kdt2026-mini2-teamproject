package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
    String URL = "jdbc:mysql://localhost:3306/SmartLocker";
    String USER = "root";
    String PASSWORD = "1234";

    protected Connection conn;

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("--> DB 연동 성공!"); 
        } catch(Exception e) {
            System.out.println("--> DB 연동 실패: " + e);
        }
    }
    protected BaseDAO() { 
        connect(); 
    }
}

