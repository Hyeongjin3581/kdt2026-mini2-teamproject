package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.dto.RentalDTO;
import controller.StudentController;

public class RentalStudentView {
    private int u_no;
    private RentalStudentView(){} // 1.
    private static final RentalStudentView instance = new RentalStudentView(); // 2.
    public static RentalStudentView getInstance(){return instance;} // 3.  


    // 싱글톤 호출
    private StudentController sc = StudentController.getInstance();
    private Scanner scan = new Scanner(System.in);

    // 임시용 최종 루프 / RentalView 화면 구축. 
    public void run( ){
        while(true){
            try{
                System.out.print(
                        "\n==========================================" +
                        "\n 사용자 대여&반납 관리" +
                        "\n==========================================" +
                        "\n 1. 대여신청" +
                        "\n 2. 장비반납 신청" +
                        "\n 3. 사용자 대여현황 조회" +
                        "\n 0. 이전 메뉴" +
                        "\n==========================================" +
                        "\n 선택 : "
                );
                int ch = scan.nextInt();
                if( ch == 1 ){ rentalAdd(); }
                else if( ch == 2 ){ returnUpdate(); }
                else if( ch == 3){ uRentListPrint(); }
                else if( ch == 0){ return; }
                else{ System.out.println("잘못된 입력");}
            }catch(InputMismatchException e ){
                scan = new Scanner(System.in);
                System.out.println("[다시입력] "  + e);
            }
        }
    } // run end


    // [2] 대여신청 (사용자)
    public void rentalAdd(){

    System.out.println("\n========== 대여 신청 ==========");
    System.out.println("본인의 회원번호 : " + u_no);
    System.out.print("장비번호 입력 : ");
    int 장비번호 = scan.nextInt();

    RentalDTO rentalDTO = new RentalDTO(u_no, 장비번호);
    boolean result = sc.rentalAdd(rentalDTO);

    if(result){
        System.out.println("등록성공");
    } else{
        System.out.println("등록실패");
        }
    }
    //[3] 장비반납신청(사용자)
    public void returnUpdate(){
        System.out.println("대여번호 입력"); int 대여번호 = scan.nextInt();
        System.out.println("장비상태 입력"); String 장비상태 = scan.next();
        RentalDTO rentalDTO = new RentalDTO( 대여번호,장비상태 );
        boolean result = sc.returnUpdate(rentalDTO);
        if(result){System.out.println("반납성공");}
        else{System.out.println("반납실패");}
    }

    //[4] 사용자 단일 대여현황 조회( 사용자 & 관리자 )
    public void uRentListPrint(){
        System.out.print("조회할 사용자번호 : "); int 조회번호 = scan.nextInt();
        ArrayList<RentalDTO> userResult = sc.uRentListPrint(조회번호);
        for(RentalDTO dto : userResult){
            System.out.println("|" +dto.getR_no()+"/"+dto.getE_no()+"/"+dto.getR_date()
            +"/"+dto.getR_due_date()+"/"+dto.getR_return_date()+"/"+dto.getR_status()+"/"+dto.getR_condition()+ "|");
        }
    } // [4] end

} // class end

// 해당 코드는 원본으로, 기능구현 확인으로 사용함.
