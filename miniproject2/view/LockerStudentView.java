package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.StudentController;
import model.dto.EquipmentDTO;
import model.dto.LockerDTO;

public class LockerStudentView {

    // 싱글톤
    private LockerStudentView() {}

    private static final LockerStudentView instance
            = new LockerStudentView();

    public static LockerStudentView getInstance() {
        return instance;
    }


    // 컨트롤러
    private StudentController studentController
            = StudentController.getInstance();


    // 입력
    private Scanner scan = new Scanner(System.in);


    // ==============================
    // 학생 보관함 메인 메뉴
    // ==============================
    public void run() {

        while (true) {

            System.out.println();
            System.out.println("=============================");
            System.out.println("          보관함 조회");
            System.out.println("=============================");
            System.out.println("1. 전체 보관함 조회");
            System.out.println("2. 보관함 상세 조회");
            System.out.println("3. 보관함 장비 조회");
            System.out.println("0. 이전 메뉴");
            System.out.println("=============================");
            System.out.print("선택 : ");

            int ch = scan.nextInt();


            if (ch == 1) {

                l_findAll();

            } else if (ch == 2) {

                l_find();

            } else if (ch == 3) {

                l_equipmentfind();

            } else if (ch == 0) {

                break;

            } else {

                System.out.println("잘못된 번호입니다.");
            }
        }
    }


    // ==============================
    // 1. 전체 보관함 조회
    // ==============================
    public void l_findAll() {

        ArrayList<Object> list= studentController.l_findAll();


        System.out.println();
        System.out.println("===== 전체 보관함 조회 =====");


        if (list.isEmpty()) {

            System.out.println(
                    "등록된 보관함이 없습니다."
            );

            return;
        }


        for (Object obj : list) {

            LockerDTO lockerDTO
                    = (LockerDTO) obj;

            System.out.println(lockerDTO);
        }
    }


    // ==============================
    // 2. 보관함 상세 조회
    // ==============================
    public void l_find() {

        System.out.println();
        System.out.println("===== 보관함 상세 조회 =====");

        System.out.print("보관함 번호 : ");
        int l_No = scan.nextInt();


        LockerDTO lockerDTO
                = studentController.l_find(l_No);


        if (lockerDTO != null) {

            System.out.println(lockerDTO);

        } else {

            System.out.println(
                    "존재하지 않는 보관함입니다."
            );
        }
    }


    // ==============================
    // 3. 보관함 장비 조회
    // ==============================
    public void l_equipmentfind() {

        System.out.println();
        System.out.println("===== 보관함 장비 조회 =====");

        System.out.print("보관함 번호 : ");
        int l_No = scan.nextInt();


        EquipmentDTO equipmentDTO
                = studentController.l_equipmentfind(
                        l_No
                );


        if (equipmentDTO != null) {

            System.out.println(equipmentDTO);

        } else {

            System.out.println(
                    "해당 보관함에 장비가 없습니다."
            );
        }
    }

}