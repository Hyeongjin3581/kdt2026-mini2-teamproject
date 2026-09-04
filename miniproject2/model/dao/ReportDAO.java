package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.RentalDTO;
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
    public ArrayList<ReportDTO> report_findAll( ){
        ArrayList<ReportDTO> list = new ArrayList<>(); // 2.7 레코드 정보들을 담을 리스트
        try {
            String sql = "select report.report_id, report.r_no, report.report_type, "
        + "report.description, rental.r_return_date, report.status "
        + "from report join rental "
        + "on report.r_no = rental.r_no";   
            PreparedStatement ps = conn.prepareStatement( sql ); 
            ResultSet rs = ps.executeQuery(); 
            
            while ( rs.next() ) { 
                
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setReportId( rs.getInt("report_id"));
                reportDTO.setRNo( rs.getInt("r_no"));
                reportDTO.setReportType( rs.getString("report_type"));
                reportDTO.setDescription( rs.getString("description"));
                reportDTO.setrReturnDate( rs.getString("r_return_date")); 
                reportDTO.setStatus( rs.getString("status"));
                
                list.add( reportDTO );
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        } // 2.7 리스트 반환
        return list;
    } // 전체조회 end

    // [3] 신고 상세 조회 DAO
    public ReportDTO report_find(int reportId) {

    try {
        String sql = "select report.report_id, report.r_no, report.report_type, "
                + "report.description, rental.r_return_date, report.status "
                + "from report join rental "
                + "on report.r_no = rental.r_no "
                + "where report.report_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, reportId);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            ReportDTO reportDTO = new ReportDTO();

            reportDTO.setReportId(rs.getInt("report_id"));
            reportDTO.setRNo(rs.getInt("r_no"));
            reportDTO.setReportType(rs.getString("report_type"));
            reportDTO.setDescription(rs.getString("description"));
            reportDTO.setrReturnDate(rs.getString("r_return_date"));
            reportDTO.setStatus(rs.getString("status"));

            return reportDTO;
        }
    } catch(SQLException e) {
        System.out.println(e);
    }
    return null;
}

    // [4] 신고 유형별 조회 DAO
    public ArrayList<ReportDTO> report_typeFind(String reportType) {
        ArrayList<ReportDTO> list = new ArrayList<>();

    try {
        String sql = "select report.report_id, report.r_no, report.report_type, "
                + "report.description, rental.r_return_date, report.status "
                + "from report join rental "
                + "on report.r_no = rental.r_no "
                + "where report.report_type = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, reportType);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            ReportDTO reportDTO = new ReportDTO();

            reportDTO.setReportId(rs.getInt("report_id"));
            reportDTO.setRNo(rs.getInt("r_no"));
            reportDTO.setReportType(rs.getString("report_type"));
            reportDTO.setDescription(rs.getString("description"));
            reportDTO.setrReturnDate(rs.getString("r_return_date"));
            reportDTO.setStatus(rs.getString("status"));

            list.add(reportDTO);
        }
    } catch(SQLException e) {
        System.out.println(e);
    }
    return list;
    }

    // [5] 처리 상태별 조회
    public ArrayList<ReportDTO> report_statusFind(String status) {
        ArrayList<ReportDTO> list = new ArrayList<>();

    try {
        String sql = "select report.report_id, report.r_no, report.report_type, "
                + "report.description, rental.r_return_date, report.status "
                + "from report join rental "
                + "on report.r_no = rental.r_no "
                + "where report.status = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, status);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            ReportDTO reportDTO = new ReportDTO();

            reportDTO.setReportId(rs.getInt("report_id"));
            reportDTO.setRNo(rs.getInt("r_no"));
            reportDTO.setReportType(rs.getString("report_type"));
            reportDTO.setDescription(rs.getString("description"));
            reportDTO.setrReturnDate(rs.getString("r_return_date"));
            reportDTO.setStatus(rs.getString("status"));

            list.add(reportDTO);
        }
    } catch(SQLException e) {
        System.out.println(e);
    }
    return list;
    }  

    // [6] 신고 처리상태 변경
    public boolean report_statusUpdate(int reportId, String status) {
    try {
        String sql = "update report set status = ? where report_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, status);
        ps.setInt(2,reportId);

        int result = ps.executeUpdate();
        if ( result == 1 ) return true;
    } catch(SQLException e) {
        System.out.println(e);
    }
    return false;
    }

    // [7] 장비 최근 이용내역 조회
    public RentalDTO recentRental_find(int eNo) {

    try {
        String sql = "select u_no, r_date, r_return_date "
                + "from rental "
                + "where e_no = ? "
                + "order by r_date desc "
                + "limit 1";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, eNo);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            RentalDTO rentalDTO = new RentalDTO();

            rentalDTO.setU_no(rs.getInt("u_no"));
            rentalDTO.setR_date(rs.getString("r_date"));
            rentalDTO.setR_return_date(rs.getString("r_return_date"));

            return rentalDTO;
        }

    } catch(SQLException e) {
        System.out.println(e);
    }

    return null;
}
} // class end
