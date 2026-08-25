package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import model.dto.RentalDTO;
import model.dto.RentalTime;


// BaseDAO가 class로 구현되어, conn변수를 그대로 재사용. 
public class RentalDAO extends BaseDAO {
    private RentalDAO(){
        super();    // 부모인 BaseDAO의 생성자 호출. Why? 
    };  // connect() 메서드(DB 연결 실행 코드)가 들어있기 때문. 
    // 해당 구문 누락시 conn이 null값으로 남아 DB연동이 안됨.
    private static final RentalDAO instance = new RentalDAO();
    public static RentalDAO getInstance() { return instance; }  


    // [1] 대여번호 유효성 검사
    public boolean rentalNoCheck(int rNo) {
        boolean result = false;
        
        // 1. SQL문 작성 (가변 데이터는 ?로)
        String sql = "SELECT r_no FROM rental WHERE r_no = ?";
        // JDBC 핵심 객체 선언
        PreparedStatement ps = null;
        ResultSet rs = null;
        

        // 8월 13일 강의 참고 .
        try {
            // 2. SQL 객체 생성
            ps = conn.prepareStatement(sql);
            // 3. ?바인딩
            ps.setInt(1, rNo);
            // 4. SQL 실행 및 결과 받아오기  / excute --> 실행 // excuteUpdate --> ps( SQL기재된 인터페이스 SQL 실행)(반환타입 int임.) 성공 : 1 , 실패 : 0
            rs = ps.executeQuery();
            // 5. 결과 확인 (데이터가 존재하면 true)
            if (rs.next()) {
                result = true;
            }
            
        } catch (Exception e) {
            System.out.println("대여번호 확인 중 오류 : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 6. 닫기 
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace(); // 오류 발생시 빨간줄로 설명해줌. 
            }
        }
        return result;
    } // rentalNoCheck end

//[2] 전체 대여목록 조회
public ArrayList<RentalDTO>rentalListPrint(){
    // RentalDto를 매개변수타입으로 지정. 
    ArrayList<RentalDTO> rentalList = new ArrayList<>();
    


    // 기재된 sql 문 반환. 
    String sql = "SELECT * FROM rental";
    PreparedStatement ps = null;







} // rentalListPrint end



}   // class end
