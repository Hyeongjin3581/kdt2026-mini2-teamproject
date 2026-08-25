package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dto.ReportDTO;

public class ReportDAO extends BaseDAO{
    private ReportDAO(){}
    private static final ReportDAO instance = new ReportDAO();
    public static ReportDAO getInstance(){ return instance; }

    // [1] DAO 등록
    public boolean report_add( ReportDTO reportDTO ){
        try{
            // 1.1 SQL 작성 , 값에 와일드카드(?) 이용한 매개변수 대입
            String sql = "insert into report(r_no, report_type, description) values( ? , ? , ? )";
            // 1.2 연동된 데이터베이스에 SQL 기재, 예외 필수
            PreparedStatement ps = conn.prepareStatement(sql); // conn 멤버변수는 BaseDAO에게 물려받음.
            // 1.3 기재된 SQL 문법내 ?(와일드카드) 매개변수 값 대입, ps.set타입( ? 순서변호, 값 );
            ps.setInt(1, reportDTO.getRNo() );
            ps.setString(2, reportDTO.getReportType() );
            ps.setString(3, reportDTO.getDescription() );
            // 1.4 기재된 SQL 실행
            int result = ps.executeUpdate();
            // 1.5 SQL 결과
            if( result == 1 ) return true;
        }catch( SQLException e ){ System.out.println( e ); }
        // 1.5 SQL 결과
        return false;
    } // 등록 end

    // [2] 전체조회 DAO

} // class end
