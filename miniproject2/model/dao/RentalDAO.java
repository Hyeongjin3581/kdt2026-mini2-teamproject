package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.dto.RentalDTO;


// BaseDAO가 class로 구현되어, conn변수를 그대로 재사용. 
public class RentalDAO extends BaseDAO {
    // 싱글톤 생성
    private RentalDAO(){}
    private static final RentalDAO instance = new RentalDAO();
    public static RentalDAO getInstance() { return instance; }  

    // [1] 대여번호 유효성 검사
    public boolean rentalNoCheck(int r_no) {
        boolean result = false;
        String sql = "SELECT r_no FROM rental WHERE r_no = ?";
        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
    
    
            ps.setInt(1, r_no);
            // 4. SQL 실행 및 결과 받아오기  / excute --> 실행 // excuteUpdate --> ps( SQL기재된 인터페이스 SQL 실행)(반환타입 int임.) 성공 : 1 , 실패 : 0
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e);} 
        return result;
    } // [1] end

    //[2] 전체 대여목록 조회
    public  ArrayList<RentalDTO>rentalListPrint(){
        // RentalDto를 매개변수타입으로 지정. 
        ArrayList<RentalDTO> rentalList = new ArrayList<>();
    
        try {
            String sql = "SELECT * FROM Rental";
            PreparedStatement ps = conn.prepareStatement( sql ); 
            ResultSet rs =  ps.executeQuery();  // 2.4 기재된 SQL 실행 , .executeQuery() select
            // 2.5 SQL 결과( select 조회 결과는 항상 테이블로 반환한다. ) 즉] 레코드 하나씩 타입변환
            while( rs.next() ){ // rs.next() : 다음 레코드(행) 이동 , 마지막 레코드까지 하나씩 이동 반복 뜻 // 레코드 수만큼 반복
                // 2.6 현재 레코드의 필드값 들을 --> DTO 변환
                RentalDTO rantalDto = new RentalDTO(); 
                rantalDto.setR_no( rs.getInt("r_no") ); // rs.get타입( "가져올속성명" )
                rantalDto.setU_no( rs.getInt("u_no") );
                rantalDto.setE_no( rs.getInt("e_no") );
                rantalDto.setR_date(rs.getString("r_date"));
                rantalDto.setR_due_date(rs.getString("r_due_date"));
                rantalDto.setR_return_date(rs.getString("r_return_date"));
                rantalDto.setR_status(rs.getString("r_status"));
                rantalDto.setR_condition(rs.getString("r_condition"));
                // 2.7 변환한 DTO --> 리스트에 담기
                rentalList.add( rantalDto );
            }
        }catch( SQLException e ){ System.out.println(e); } 
        return rentalList; 
    } // [2] end


    // [3] 대여 신청
    public boolean rentalAdd(RentalDTO rentalDTO) {
         String sql = "INSERT INTO rental (u_no, e_no) VALUES (?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            // 바인딩
            ps.setInt(1, rentalDTO.getU_no());
            ps.setInt(2, rentalDTO.getE_no());
            
            // SQL 실행
            int result = ps.executeUpdate(); 
        
            // 결과 확인
            if (result == 1) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {System.out.println(e);}
        return false;
    } // [3] end

    //[4] 장비 반납신청
    public boolean returnUpdate(RentalDTO rentalDTO){
        String sql = "update rental set r_condition = ? , r_status = '반납완료'  WHERE r_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, rentalDTO.getR_condition());
            ps.setInt(2, rentalDTO.getR_no());

            int result = ps.executeUpdate(); 

            if (result == 1){return true;}
        } catch (SQLException e) {System.out.println(e);}
        return false;
    } // [4] end

    // [5] 사용자 개인 대여현황 조회 
    public ArrayList<RentalDTO> uRentListPrint(int u_no) {
        ArrayList<RentalDTO> uRentList = new ArrayList<>();
        String sql = "SELECT r_no, e_no, r_date, r_due_date, r_return_date, r_status, r_condition FROM rental WHERE u_no = ?"; 

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, u_no); 

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) { 
                    RentalDTO rentalDto = new RentalDTO(); 
                    rentalDto.setR_no(rs.getInt("r_no")); 
                    rentalDto.setE_no(rs.getInt("e_no"));
                    rentalDto.setR_date(rs.getString("r_date"));
                    rentalDto.setR_due_date(rs.getString("r_due_date"));
                    rentalDto.setR_return_date(rs.getString("r_return_date"));
                    rentalDto.setR_status(rs.getString("r_status"));
                    rentalDto.setR_condition(rs.getString("r_condition"));
                    
                    uRentList.add(rentalDto);
                }
            }
        } catch (Exception e) {System.out.println(e);}
        return uRentList;
    } // [5] end

}   // class end