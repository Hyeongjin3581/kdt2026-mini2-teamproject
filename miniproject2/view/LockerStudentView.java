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

    // 학생 보관함 조회 실행
    public void run() {

        l_findAll();

    }


    // 전체 보관함 조회
    public void l_findAll() {

        ArrayList<Object> list
                = studentController.l_findAll();

        System.out.println();
        System.out.println("===== 전체 보관함 조회 =====");

        if (list.isEmpty()) {

            System.out.println("등록된 보관함이 없습니다.");
            return;
        }

        for (Object obj : list) {

            LockerDTO lockerDTO
                    = (LockerDTO) obj;

            System.out.println();
            System.out.println("-----------------------------");

            System.out.println(
                    "보관함 번호 : " + lockerDTO.getL_No()
            );

            System.out.println(
                    "보관함 위치 : " + lockerDTO.getL_Location()
            );

            System.out.println(
                    "보관함 상태 : " + lockerDTO.getL_Status()
            );


            EquipmentDTO equipmentDTO
                    = studentController.l_equipmentfind(
                            lockerDTO.getL_No()
                    );


            if (equipmentDTO != null) {

                System.out.println(
                        "장비 번호 : " + equipmentDTO.getE_No()
                );

                System.out.println(
                        "장비 이름 : " + equipmentDTO.getE_Name()
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