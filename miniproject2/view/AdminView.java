package view;

import java.util.Scanner;

import view.RentalAdminView;

public class AdminView {

    private  AdminView(){};
    private static final AdminView instance = new AdminView();
    public static AdminView getInstance() { return instance; }

    private Scanner scan = new Scanner(System.in);

    // 관리자 메인 화면
    public void run() {

        while (true) {

            System.out.println("=================================");
            System.out.println("          관리자 메뉴");
            System.out.println("=================================");
            System.out.println("1. 장비 / 보관함 / 대여 관리");
            System.out.println("2. 회원 관리");
            System.out.println("3. 고장 및 파손 신고 관리");
            System.out.println("0. 로그아웃");
            System.out.println("=================================");
            System.out.print("선택 : ");

            int ch = scan.nextInt();

            if (ch == 1) {  // 1번 실행시 나오는 화면
                System.out.println("=================================");
                System.out.println("1. 장비");
                System.out.println("2. 보관함");
                System.out.println("3. 대여 관리");
                System.out.println("0. 이전화면");
                System.out.println("=================================");
                System.out.print("선택 : ");

                int ch2 = scan.nextInt();

                if(ch2 == 1){
                    //[1] 장비 view 실행
                    EquipmentView.getInstance().run();
                }
                else if( ch2 == 2){
                    //[2] 보관함 view 실행 
                    LockerView.getInstance().run();
                }
                else if( ch2 == 3 ){
                    //[3] 대여관리 실행
                    RentalAdminView.getInstance().run();
                }
                else if( ch2== 0 ){
                    break;
                }
                else{ 
                    System.out.println("잘못된 입력입니다. ");
                }
            }

            else if (ch == 2) { // 회원관리
                // userView 생성 혹은 컨트롤러에서 받아서 활용요망. 
                AdminUserView.getInstance().run();
            } 
            else if (ch == 3) { // 고장 및 파손 신고 관리. ( 해당 메서드 생성한 거 가져와서 하는게 좋을듯.)
                // ReportView.
                // ReportView.getInstance().run(); <-- 이거 말고 그냥 report에서 가져오셈 모르면 물어보고
                System.out.println("[ 고장 및 파손 신고 관리 ]");
            } 
            else if (ch == 0) { // 로그아웃
                System.out.println("로그아웃 합니다.");
                break;
            } 
            else {
                System.out.println("잘못된 번호입니다.");
            }
        }
    }
}