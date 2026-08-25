package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try{
            // 기재된 sql 문 반환. 
            String sql = "SELECT * FROM rental";
            PreparedStatement ps = conn.prepareStatement( sql );
            ResultSet rs =  ps.executeQuery(); // 2.4 기재된 SQL 실행 , .executeQuery() select
            // 2.5 SQL 결과( select 조회 결과는 항상 테이블로 반환한다. ) 즉] 레코드 하나씩 타입변환
            while( rs.next() ){ // rs.next() : 다음 레코드(행) 이동 , 마지막 레코드까지 하나씩 이동 반복 뜻 // 레코드 수만큼 반복
                // 2.6 현재 레코드의 필드값 들을 --> DTO 변환
                RentalDTO rantalDto = new RentalDTO(); 
                rantalDto.setrNo( rs.getInt("rno") ); // rs.get타입( "가져올속성명" )
                rantalDto.setuNo( rs.getInt("uNo") );
                rantalDto.seteNo( rs.getInt("eNo") );
                rantalDto.setRentalTime(rs.getRentalTime("rentalTime"));
                rantalDto.setrStatus(rs.getString("rStatus"));
                rantalDto
                // 2.7 변환한 DTO --> 리스트에 담기
                list.add( rantalDto );
            }
        }catch( SQLException e ){ System.out.println(e); } 
        // 2.8 리스트 반환
        return list; 

         }
    } // rentalListPrint end
}   // class end
