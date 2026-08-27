package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.StudentController;
import controller.LoginController;
import model.dto.EquipmentDTO;
import model.dto.RentalDTO;

public class StudentView {
    private StudentView(){};
    private static final StudentView instance = new StudentView();
    public static StudentView getInstance() { return instance; }

    private StudentController sc = StudentController.getInstance();
    private Scanner scan = new Scanner(System.in);


    public void run(){
        while (true) {
            try{
                System.out.println();
                System.out.println("┌──────────────────────────────────────┐");
                System.out.println("│          SmartLocker System                           │");
                System.out.println("├──────────────────────────────────────┤");
                System.out.println("│                                                                     │");
                System.out.println("│  1. 장비 목록 조회                                       │");
                System.out.println("│  2. 장비 검색                                               │");
                System.out.println("│  3. 장비 대여                                               │");
                System.out.println("│  4. 내 대여 현황                                          │");
                System.out.println("│  5. 장비 반납                                               │");
                System.out.println("│  6. 로그아웃                                                │");
                System.out.println("│                                                                     │");
                System.out.println("└──────────────────────────────────────┘");
                System.out.print(" 메뉴 선택 : ");

                int ch = scan.nextInt();

                if ( ch == 1 ) {
                    e_findAll();
                } else if ( ch == 2 ) {
                    e_search();
                } else if ( ch == 3 ) {
                    rentalAdd();
                } else if ( ch == 4 ) {
                    myRentalList();
                } else if ( ch == 5 ) {
                    returnEquipment();
                } else if ( ch == 6 ) {
                    System.out.println("로그아웃");
                    break;
                } else {
                    System.out.println("잘못된 번호입니다.");
                }

            }catch( Exception e ){ System.out.println( e ); }
        }
    }

    // 다운캐스팅
    // 1. 전체 장비 조회
    public void e_findAll() {
        ArrayList<Object> list = sc.e_findAll();

        System.out.println();
        System.out.println("===== 전체 장비 조회 =====");

        for (Object obj : list) {
            EquipmentDTO equipmentDTO = (EquipmentDTO) obj;
            System.out.println(equipmentDTO);
        }
    }


    // 2. 장비 검색 (카테고리 기준)
    public void e_search() {
        System.out.println();
        System.out.println("===== 장비 검색 =====");
        System.out.print("장비 카테고리 입력 : ");
        String category = scan.next();

        ArrayList<EquipmentDTO> list = sc.e_categoryfind(category);

        if (list.isEmpty()) {
            System.out.println("해당 카테고리의 장비가 없습니다.");
        } else {
            for (EquipmentDTO dto : list) {
                System.out.println(dto);
            }
        }
    }


    // 3. 장비 대여
    public void rentalAdd() {
        System.out.println();
        System.out.println("===== 장비 대여 =====");
        System.out.print("대여할 장비번호 입력 : ");
        int eNo = scan.nextInt();

        int uNo = LoginController.getInstance().getLoginUser().getU_no();

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setU_no(uNo);
        rentalDTO.setE_no(eNo);

        boolean result = sc.rentalAdd(rentalDTO);

        if (result) {
            System.out.println("대여 신청이 완료되었습니다.");
        } else {
            System.out.println("대여 신청에 실패했습니다.");
        }
    }


    // 4. 내 대여 현황
    public void myRentalList() {
        int uNo = LoginController.getInstance().getLoginUser().getU_no();

        ArrayList<RentalDTO> list = sc.uRentListPrint(uNo);

        System.out.println();
        System.out.println("===== 내 대여 현황 =====");

        if (list.isEmpty()) {
            System.out.println("대여 내역이 없습니다.");
        } else {
            for (RentalDTO dto : list) {
                System.out.println(dto);
            }
        }
    }


    // 5. 장비 반납
    public void returnEquipment() {
        System.out.println();
        System.out.println("===== 장비 반납 =====");
        System.out.print("반납할 대여번호 입력 : ");
        int rNo = scan.nextInt();

        System.out.print("장비 상태 (정상/이상있음) : ");
        String condition = scan.next();

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setR_no(rNo);
        rentalDTO.setR_condition(condition);

        boolean result = sc.returnUpdate(rentalDTO);

        if (result) {
            System.out.println("반납이 완료되었습니다.");
        } else {
            System.out.println("반납에 실패했습니다.");
        }
    }
}