package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

// 연동 정보
public class BaseDAO {
    String URL = "jdbc:mysql://localhost:3306/SmartLocker";
    String USER = "root";
    String PASSWORD = "1234";
    // 연동 인터페이스
    protected Connection conn;
    // 연동 메소드
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("--> DB 연동 성공!"); 
        } catch(Exception e) {
            System.out.println("--> DB 연동 실패: " + e);
        }
    }
    // 기본 생성자 연동 메소드
    protected BaseDAO() { connect(); }
}

