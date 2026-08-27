package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.AdminController;
import model.dto.EquipmentDTO;
import model.dto.LockerDTO;

public class LockerAdminView {

    // 싱글톤
    private LockerAdminView() {}
    private static final LockerAdminView instance = new LockerAdminView();
    public static LockerAdminView getInstance() {return instance;}

    // 컨트롤러
    private AdminController adminController = AdminController.getInstance();

    // 입력
    private Scanner scan = new Scanner(System.in);

    // ==============================
    // 관리자 보관함 메인 메뉴
    // ==============================
    public void run() {

        while (true) {

            System.out.println();
            System.out.println("=============================");
            System.out.println("        관리자 보관함 관리");
            System.out.println("=============================");
            System.out.println("1. 전체 보관함 조회");
            System.out.println("2. 보관함 상세 조회");
            System.out.println("3. 보관함 등록");
            System.out.println("4. 보관함 수정");
            System.out.println("5. 보관함 상태 변경");
            System.out.println("6. 보관함 삭제");
            System.out.println("7. 보관함 장비 조회");
            System.out.println("0. 이전 메뉴");
            System.out.println("=============================");
            System.out.print("선택 : ");

            int ch = scan.nextInt();


            if (ch == 1) {

                l_findAll();

            } else if (ch == 2) {

                l_find();

            } else if (ch == 3) {

                l_add();

            } else if (ch == 4) {

                l_update();

            } else if (ch == 5) {

                l_statusUpdate();

            } else if (ch == 6) {

                l_delete();

            } else if (ch == 7) {

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

        ArrayList<Object> list
                = adminController.l_findAll();

        System.out.println();
        System.out.println("===== 전체 보관함 조회 =====");


        if (list.isEmpty()) {

            System.out.println("등록된 보관함이 없습니다.");
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
                = adminController.l_find(l_No);


        if (lockerDTO != null) {

            System.out.println(lockerDTO);

        } else {

            System.out.println("존재하지 않는 보관함입니다.");
        }
    }


    // ==============================
    // 3. 보관함 등록
    // ==============================
    public void l_add() {

        System.out.println();
        System.out.println("===== 보관함 등록 =====");

        System.out.print("보관함 위치 : ");
        String l_Location = scan.next();

        System.out.print("보관함 상태 : ");
        String l_Status = scan.next();


        LockerDTO lockerDTO
                = new LockerDTO();

        lockerDTO.setL_Location(l_Location);
        lockerDTO.setL_Status(l_Status);


        boolean result
                = adminController.l_add(lockerDTO);


        if (result) {

            System.out.println("보관함 등록 성공");

        } else {

            System.out.println("보관함 등록 실패");
        }
    }


    // ==============================
    // 4. 보관함 수정
    // ==============================
    public void l_update() {

        System.out.println();
        System.out.println("===== 보관함 수정 =====");

        System.out.print("수정할 보관함 번호 : ");
        int l_No = scan.nextInt();


        // 수정은 존재 여부 검사
        if (!adminController.l_NoCheck(l_No)) {

            System.out.println("존재하지 않는 보관함입니다.");
            return;
        }


        System.out.print("보관함 위치 : ");
        String l_Location = scan.next();

        System.out.print("보관함 상태 : ");
        String l_Status = scan.next();


        LockerDTO lockerDTO
                = new LockerDTO();

        lockerDTO.setL_No(l_No);
        lockerDTO.setL_Location(l_Location);
        lockerDTO.setL_Status(l_Status);


        boolean result
                = adminController.l_update(lockerDTO);


        if (result) {

            System.out.println("보관함 수정 성공");

        } else {

            System.out.println("보관함 수정 실패");
        }
    }


    // ==============================
    // 5. 보관함 상태 변경
    // ==============================
    public void l_statusUpdate() {

        System.out.println();
        System.out.println("===== 보관함 상태 변경 =====");

        System.out.print("보관함 번호 : ");
        int l_No = scan.nextInt();


        if (!adminController.l_NoCheck(l_No)) {

            System.out.println("존재하지 않는 보관함입니다.");
            return;
        }


        System.out.print("변경할 상태 : ");
        String l_Status = scan.next();


        boolean result
                = adminController.l_statusupdate(
                        l_No,
                        l_Status
                );


        if (result) {

            System.out.println("보관함 상태 변경 성공");

        } else {

            System.out.println("보관함 상태 변경 실패");
        }
    }


    // ==============================
    // 6. 보관함 삭제
    // ==============================
    public void l_delete() {

        System.out.println();
        System.out.println("===== 보관함 삭제 =====");

        System.out.print("삭제할 보관함 번호 : ");
        int l_No = scan.nextInt();


        if (!adminController.l_NoCheck(l_No)) {

            System.out.println("존재하지 않는 보관함입니다.");
            return;
        }


        boolean result
                = adminController.l_delete(l_No);


        if (result) {

            System.out.println("보관함 삭제 성공");

        } else {

            System.out.println("보관함 삭제 실패");
        }
    }


    // ==============================
    // 7. 보관함 장비 조회
    // ==============================
    public void l_equipmentfind() {

        System.out.println();
        System.out.println("===== 보관함 장비 조회 =====");

        System.out.print("보관함 번호 : ");
        int l_No = scan.nextInt();


        EquipmentDTO equipmentDTO
                = adminController.l_equipmentfind(l_No);


        if (equipmentDTO != null) {

            System.out.println(equipmentDTO);

        } else {

            System.out.println(
                    "해당 보관함에 장비가 없습니다."
            );
        }
    }

}