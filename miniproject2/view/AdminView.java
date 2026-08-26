package view;

import java.util.Scanner;

import view.rentalAdminView.RentalAdminView;

public class AdminView {

    private Scanner scan = new Scanner(System.in);

    // 관리자 메인 화면
    public void index() {

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

            if (ch == 1) {

                // 하위 View 실행
                RentalAdminView.getInstance().index();

            } else if (ch == 2) {

                System.out.println("[ 회원 관리 ]");

            } else if (ch == 3) {

                System.out.println("[ 고장 및 파손 신고 관리 ]");

            } else if (ch == 0) {

                System.out.println("로그아웃 합니다.");
                break;

            } else {

                System.out.println("잘못된 번호입니다.");

            }
        }
    }
}