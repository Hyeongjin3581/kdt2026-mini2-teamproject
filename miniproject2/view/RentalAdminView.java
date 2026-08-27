package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.dto.RentalDTO;
import controller.AdminController;

public class RentalAdminView {
    private RentalAdminView(){} // 1.
    private static final RentalAdminView instance = new RentalAdminView(); // 2.
    public static RentalAdminView getInstance(){return instance;} // 3.  


    private AdminController ac = AdminController.getInstance();
    // scanner 설정 
    private Scanner scan = new Scanner(System.in);

    // 최종 루프 / RentalView 화면 구축. 
    public void run( ){
        while(true){
            try{
                System.out.print(
                        "\n==========================================" +
                        "\n 관리자 대여 관리" +
                        "\n==========================================" +
                        "\n 1. 전체 대여목록 조회" +
                        "\n 2. 사용자 단일 대여현황 조회" +
                        "\n 0. 이전 메뉴" +
                        "\n==========================================" +
                        "\n 선택 : "
                );
                int ch = scan.nextInt();
                if( ch==1 ){ rentalListPrint(); }
                else if( ch == 2){ uRentListPrint(); }
                else if( ch == 0){ return; }
                else{ System.out.println("잘못된 입력");}
            }catch(InputMismatchException e ){
                scan = new Scanner(System.in);
                System.out.println("[다시입력] "  + e);
            }
        }
    } // run end

    // [1] 전체 대여 목록 조회 ( 관리자용 )
    public void rentalListPrint(){

        ArrayList<RentalDTO> result = ac.rentalListPrint();

        System.out.println();
        System.out.println("======================================");
        System.out.println("                              전체 대여 목록");
        System.out.println("======================================");

        if(result.isEmpty()){
            System.out.println(" 대여 내역이 없습니다.");
        }
        else{

            System.out.printf("%-8s %-8s %-8s %-20s %-20s %-20s %-12s %-10s%n",
                    "대여번호",
                    "회원번호",
                    "장비번호",
                    "대여일",
                    "반납예정일",
                    "반납일",
                    "대여상태",
                    "장비상태");

            System.out.println("----------------------------------------------------");

            for(RentalDTO dto : result){

                System.out.printf("%-8d %-8d %-8d %-20s %-20s %-20s %-12s %-10s%n",
                        dto.getR_no(),
                        dto.getU_no(),
                        dto.getE_no(),
                        dto.getR_date(),
                        dto.getR_due_date(),
                        dto.getR_return_date(),
                        dto.getR_status(),
                        dto.getR_condition());
            }
        }

        System.out.println("===================================");
    }

    // [4] 사용자 단일 대여현황 조회 (관리자용)
    public void uRentListPrint(){

        System.out.print("조회할 사용자번호 : ");
        int 조회번호 = scan.nextInt();

        ArrayList<RentalDTO> userResult = ac.uRentListPrint(조회번호);

        System.out.println();
        System.out.println("=========================================");
        System.out.println("                        사용자 대여 현황");
        System.out.println("=========================================");
        System.out.println(" 회원번호 : " + 조회번호);
        System.out.println("--------------------------------------------------");

        if(userResult.isEmpty()){
            System.out.println(" 해당 사용자의 대여 내역이 없습니다.");
        }
        else{

            System.out.printf("%-8s %-8s %-20s %-20s %-20s %-12s %-10s%n",
                    "대여번호",
                    "장비번호",
                    "대여일",
                    "반납예정일",
                    "반납일",
                    "대여상태",
                    "장비상태");

            System.out.println("--------------------------------------------------------");

            for(RentalDTO dto : userResult){

                System.out.printf("%-8d %-8d %-20s %-20s %-20s %-12s %-10s%n",
                        dto.getR_no(),
                        dto.getE_no(),
                        dto.getR_date(),
                        dto.getR_due_date(),
                        dto.getR_return_date(),
                        dto.getR_status(),
                        dto.getR_condition());
            }
        }
        System.out.println("=========================================");
    }
}

// RentalAdminView로 이동
//  RentalAdminView.getInstance().run();
