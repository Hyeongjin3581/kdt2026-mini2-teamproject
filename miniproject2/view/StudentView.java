package view;

import java.util.Scanner;

import controller.StudentController;

public class StudentView {
    private StudentView(){};
    private static final StudentView instance = new StudentView();
    public static StudentView getInstance() { return instance; }

    private StudentController sc = StudentController.getInstance();
    private Scanner scan = new Scanner(System.in);


    public void index(){
        while (true) {
            try{
                System.out.println();
                System.out.println("┌──────────────────────────────────────┐");
                System.out.println("│          SmartLocker System          │");
                System.out.println("├──────────────────────────────────────┤");
                System.out.println("│                                      │");
                System.out.println("│  1. 장비 목록 조회                    │");
                System.out.println("│  2. 장비 검색                         │");
                System.out.println("│  3. 장비 대여                         │");
                System.out.println("│  4. 내 대여 현황                      │");
                System.out.println("│  5. 장비 반납                         │");
                System.out.println("│  6. 로그아웃                          │");
                System.out.println("│                                      │");
                System.out.println("└──────────────────────────────────────┘");
                System.out.print(" 메뉴 선택 : ");

            int ch = scan.nextInt();

            }catch( Exception e ){ System.out.println( e ); }
        }
    }
}
