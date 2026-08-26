package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.dto.RentalDTO;
import controller.RentalController;

public class RentalStudentView {
    private RentalStudentView(){} // 1.
    private static final RentalStudentView instance = new RentalStudentView(); // 2.
    public static RentalStudentView getInstance(){return instance;} // 3.  
    // 싱글톤 호출
    private RentalController rc = RentalController.getInstance();
    private Scanner scan = new Scanner(System.in);

    // 최종 루프 / RentalView 화면 구축. 
    public void run( ){
        while(true){
            try{
                System.out.print("\n 1. 전체 대여목록 조회(관리자) \n 2. 대여신청(사용자) \n 3. 장비반납신청(사용자) \n 4.단일 대여현황 조회( 사용자 & 관리자 )" );
                int ch = scan.nextInt();
                if( ch==1 ){ rentalListPrint(); }
                else if( ch == 2 ){ rentalAdd(); }
                else if( ch == 3 ){ returnUpdate(); }
                else if( ch == 4){ uRentListPrint(); }
            }catch(InputMismatchException e ){
                scan = new Scanner(System.in);
                System.out.println("[다시입력] "  + e);
            }
        }
    } // run end

    // [1] 전체 대여 목록 조회 ( 관리자용 ) 
    public void rentalListPrint(){
        ArrayList<RentalDTO> result = rc.rentalListPrint();
        for(RentalDTO dto : result){
            System.out.println(dto.getR_no()+ "/"+dto.getU_no()+"/"+dto.getE_no()+"/"+dto.getR_date()
            +"/"+dto.getR_due_date()+"/"+dto.getR_return_date()+"/"+dto.getR_status()+"/"+dto.getR_condition());
        }
    } // [1] end

    // [2] 대여신청 (사용자)
    public void rentalAdd(){

    }
    //[3] 장비반납신청(사용자)
    public void returnUpdate(){}

    //[4] 사용자 단일 대여현황 조회( 사용자 & 관리자 )
    public void uRentListPrint(){}

    
}
