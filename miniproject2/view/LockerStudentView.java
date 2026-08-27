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
    private Scanner scan
            = new Scanner(System.in);


    // ==============================
    // 학생 보관함 메뉴
    // ==============================

    public void run() {

        while (true) {

            System.out.println();
            System.out.println("=============================");
            System.out.println("          보관함 조회");
            System.out.println("=============================");
            System.out.println("1. 전체 보관함 조회");
            System.out.println("0. 이전 메뉴");
            System.out.println("=============================");
            System.out.print("선택 : ");

            int ch = scan.nextInt();


            if (ch == 1) {

                l_findAll();

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

        ArrayList<Object> list
                = studentController.l_findAll();


        System.out.println();
        System.out.println("===== 전체 보관함 조회 =====");


        // 보관함이 하나도 없는 경우
        if (list.isEmpty()) {

            System.out.println("등록된 보관함이 없습니다.");
            return;
        }


        // 모든 보관함 출력
        for (Object obj : list) {

            LockerDTO lockerDTO
                    = (LockerDTO) obj;


            // 보관함 기본 정보
            System.out.println();
            System.out.println("-----------------------------");

            System.out.println(
                    "보관함 번호 : "
                    + lockerDTO.getL_No()
            );

            System.out.println(
                    "보관함 위치 : "
                    + lockerDTO.getL_Location()
            );

            System.out.println(
                    "보관함 상태 : "
                    + lockerDTO.getL_Status()
            );


            // 해당 보관함에 있는 장비 조회
            EquipmentDTO equipmentDTO
                    = studentController.l_equipmentfind(
                            lockerDTO.getL_No()
                    );


            // 장비가 있는 경우
            if (equipmentDTO != null) {

                System.out.println(
                        "장비 번호 : "
                        + equipmentDTO.getE_No()
                );

                System.out.println(
                        "장비 이름 : "
                        + equipmentDTO.getE_Name()
                );

                System.out.println(
                        "장비 카테고리 : "
                        + equipmentDTO.getE_Category()
                );

                System.out.println(
                        "장비 상태 : "
                        + equipmentDTO.getE_Status()
                );

            } else {

                System.out.println(
                        "장비 : 등록된 장비 없음"
                );
            }

            System.out.println("-----------------------------");
        }
    }
}