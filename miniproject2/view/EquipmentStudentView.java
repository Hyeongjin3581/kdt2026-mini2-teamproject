package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.StudentController;
import model.dto.EquipmentDTO;

public class EquipmentStudentView {

    // 싱글톤
    private EquipmentStudentView() {}
    private static final EquipmentStudentView instance = new EquipmentStudentView();
    public static EquipmentStudentView getInstance() {return instance;}


    // 컨트롤러
    private StudentController studentController = StudentController.getInstance();


    // 입력
    private Scanner scan = new Scanner(System.in);


    // ==================================================
    // 학생 장비 메인 메뉴
    // ==================================================
    public void run() {

        while (true) {

            System.out.println();
            System.out.println("=============================");
            System.out.println("          장비 조회");
            System.out.println("=============================");
            System.out.println("1. 전체 장비 조회");
            System.out.println("2. 장비 상세 조회");
            System.out.println("3. 카테고리별 장비 조회");
            System.out.println("4. 대여가능 장비 조회");
            System.out.println("0. 이전 메뉴");
            System.out.println("=============================");
            System.out.print("선택 : ");

            int ch = scan.nextInt();


            if (ch == 1) {

                e_findAll();

            } else if (ch == 2) {

                e_find();

            } else if (ch == 3) {

                e_categoryfind();

            } else if (ch == 4) {

                e_available();

            } else if (ch == 0) {

                break;

            } else {

                System.out.println("잘못된 번호입니다.");
            }
        }
    }




    // ==================================================
    // 1. 전체 장비 조회
    // ==================================================
    public void e_findAll() {

        ArrayList<Object> list
                = studentController.e_findAll();


        System.out.println();
        System.out.println("===== 전체 장비 조회 =====");


        if (list.isEmpty()) {

            System.out.println("등록된 장비가 없습니다.");
            return;
        }


        for (Object obj : list) {

            EquipmentDTO equipmentDTO
                    = (EquipmentDTO) obj;

            System.out.println(equipmentDTO);
        }
    }


    // ==================================================
    // 2. 장비 상세 조회
    // ==================================================
    public void e_find() {

        System.out.println();
        System.out.println("===== 장비 상세 조회 =====");

        System.out.print("장비번호 입력 : ");
        int e_No = scan.nextInt();


    }
    // ==================================================
    // 3. 카테고리별 장비 조회
    // ==================================================
    public void e_categoryfind() {

        System.out.println();
        System.out.println("===== 카테고리별 장비 조회 =====");

        System.out.print("카테고리 입력 : ");
        String e_Category = scan.next();


        ArrayList<EquipmentDTO> list
                = studentController.e_categoryfind(
                        e_Category
                );


        if (list.isEmpty()) {

            System.out.println(
                    "해당 카테고리의 장비가 없습니다."
            );

            return;
        }


        for (EquipmentDTO equipmentDTO : list) {

            System.out.println(equipmentDTO);
        }
    }


    // ==================================================
    // 4. 대여가능 장비 조회
    // ==================================================
    public void e_available() {

        ArrayList<EquipmentDTO> list
                = studentController.e_available();


        System.out.println();
        System.out.println("===== 대여가능 장비 조회 =====");


        if (list.isEmpty()) {

            System.out.println(
                    "현재 대여 가능한 장비가 없습니다."
            );

            return;
        }


        for (EquipmentDTO equipmentDTO : list) {

            System.out.println(equipmentDTO);
        }
    }


    
}

