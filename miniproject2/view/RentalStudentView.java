package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.dto.RentalDTO;
import controller.RentalController;
import controller.StudentController;

public class RentalStudentView {
    private RentalStudentView(){} // 1.
    private static final RentalStudentView instance = new RentalStudentView(); // 2.
    public static RentalStudentView getInstance(){return instance;} // 3.  


    // 싱글톤 호출
    private StudentController rc = StudentController.getInstance();


    private Scanner scan = new Scanner(System.in);

    // 임시용 최종 루프 / RentalView 화면 구축. 
    public void run( ){
        while(true){
            try{
                System.out.print("\n 1.대여신청(사용자) \n 2. 장비반납신청(사용자) \n 3.단일 대여현황 조회( 사용자 & 관리자 )" );
                int ch = scan.nextInt();
                if( ch == 1 ){ rentalAdd(); }
                else if( ch == 2 ){ returnUpdate(); }
                else if( ch == 3){ uRentListPrint(); }
            }catch(InputMismatchException e ){
                scan = new Scanner(System.in);
                System.out.println("[다시입력] "  + e);
            }
        }
    } // run end


    // [2] 대여신청 (사용자)
    public void rentalAdd(){

    }
    //[3] 장비반납신청(사용자)
    public void returnUpdate(){}

    //[4] 사용자 단일 대여현황 조회( 사용자 & 관리자 )
    public void uRentListPrint(){}

    
}
