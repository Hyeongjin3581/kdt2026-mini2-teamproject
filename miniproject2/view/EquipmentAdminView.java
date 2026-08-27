package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.AdminController;
import model.dto.EquipmentDTO;

public class EquipmentAdminView {

    // 싱글톤
    private EquipmentAdminView() {}
    private static final EquipmentAdminView instance
            = new EquipmentAdminView();

    public static EquipmentAdminView getInstance() {
        return instance;
    }


    // 컨트롤러
    private AdminController adminController
            = AdminController.getInstance();


    // 입력
    private Scanner scan = new Scanner(System.in);


    // =========================================
    // 관리자 장비 메인 메뉴
    // =========================================
    public void run() {

        while (true) {

            System.out.println();
            System.out.println("=============================");
            System.out.println("       관리자 장비 관리");
            System.out.println("=============================");
            System.out.println("1. 전체 장비 조회");
            System.out.println("2. 장비 상세 조회");
            System.out.println("3. 카테고리별 장비 조회");
            System.out.println("4. 대여가능 장비 조회");
            System.out.println("5. 장비 등록");
            System.out.println("6. 장비 수정");
            System.out.println("7. 장비 상태 변경");
            System.out.println("8. 장비 보관함 변경");
            System.out.println("9. 장비 삭제");
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

            } else if (ch == 5) {
                e_add();

            } else if (ch == 6) {
                e_update();

            } else if (ch == 7) {
                e_statusUpdate();

            } else if (ch == 8) {
                e_lockerUpdate();

            } else if (ch == 9) {
                e_delete();

            } else if (ch == 0) {
                break;

            } else {
                System.out.println("잘못된 번호입니다.");
            }
        }
    }


    // 1. 전체 장비 조회
    public void e_findAll() {

        ArrayList<Object> list
                = adminController.e_findAll();

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


    // 2. 장비 상세 조회
    public void e_find() {

        System.out.print("장비번호 입력 : ");
        int e_No = scan.nextInt();

        EquipmentDTO equipmentDTO
                = adminController.e_find(e_No);

        if (equipmentDTO != null) {

            System.out.println();
            System.out.println("===== 장비 상세 조회 =====");
            System.out.println(equipmentDTO);

        } else {

            System.out.println("존재하지 않는 장비입니다.");
        }
    }


    // 3. 카테고리별 장비 조회
    public void e_categoryfind() {

        System.out.print("카테고리 입력 : ");
        String e_Category = scan.next();

        ArrayList<EquipmentDTO> list
                = adminController.e_categoryfind(e_Category);

        System.out.println();
        System.out.println("===== 카테고리별 장비 조회 =====");

        if (list.isEmpty()) {
            System.out.println("해당 카테고리의 장비가 없습니다.");
            return;
        }

        for (EquipmentDTO equipmentDTO : list) {
            System.out.println(equipmentDTO);
        }
    }


    // 4. 대여가능 장비 조회
    public void e_available() {

        ArrayList<EquipmentDTO> list
                = adminController.e_available();

        System.out.println();
        System.out.println("===== 대여가능 장비 조회 =====");

        if (list.isEmpty()) {
            System.out.println("현재 대여 가능한 장비가 없습니다.");
            return;
        }

        for (EquipmentDTO equipmentDTO : list) {
            System.out.println(equipmentDTO);
        }
    }


    // 5. 장비 등록
    public void e_add() {

        System.out.println();
        System.out.println("===== 장비 등록 =====");

        System.out.print("장비 이름 : ");
        String e_Name = scan.next();

        System.out.print("장비 카테고리 : ");
        String e_Category = scan.next();

        System.out.print("장비 상태 : ");
        String e_Status = scan.next();

        System.out.print("보관함 번호 : ");
        int l_No = scan.nextInt();


        // 보관함 유효성 검사
        if (!adminController.l_NoCheck(l_No)) {

            System.out.println("존재하지 않는 보관함입니다.");
            return;
        }


        EquipmentDTO equipmentDTO
                = new EquipmentDTO();

        equipmentDTO.setE_Name(e_Name);
        equipmentDTO.setE_Category(e_Category);
        equipmentDTO.setE_Status(e_Status);
        equipmentDTO.setL_NO(l_No);


        boolean result
                = adminController.e_add(equipmentDTO);


        if (result) {
            System.out.println("장비 등록 성공");

        } else {
            System.out.println("장비 등록 실패");
        }
    }


    // 6. 장비 수정
    public void e_update() {

        System.out.println();
        System.out.println("===== 장비 수정 =====");

        System.out.print("수정할 장비번호 : ");
        int e_No = scan.nextInt();


        if (!adminController.e_NoCheck(e_No)) {

            System.out.println("존재하지 않는 장비번호입니다.");
            return;
        }


        System.out.print("장비 이름 : ");
        String e_Name = scan.next();

        System.out.print("장비 카테고리 : ");
        String e_Category = scan.next();

        System.out.print("장비 상태 : ");
        String e_Status = scan.next();

        System.out.print("보관함 번호 : ");
        int l_No = scan.nextInt();


        if (!adminController.l_NoCheck(l_No)) {

            System.out.println("존재하지 않는 보관함입니다.");
            return;
        }


        EquipmentDTO equipmentDTO
                = new EquipmentDTO();

        equipmentDTO.setE_No(e_No);
        equipmentDTO.setE_Name(e_Name);
        equipmentDTO.setE_Category(e_Category);
        equipmentDTO.setE_Status(e_Status);
        equipmentDTO.setL_NO(l_No);


        boolean result
                = adminController.e_update(equipmentDTO);


        if (result) {
            System.out.println("장비 수정 성공");

        } else {
            System.out.println("장비 수정 실패");
        }
    }


    // 7. 장비 상태 변경
    public void e_statusUpdate() {

        System.out.println();
        System.out.println("===== 장비 상태 변경 =====");

        System.out.print("장비번호 : ");
        int e_No = scan.nextInt();


        if (!adminController.e_NoCheck(e_No)) {

            System.out.println("존재하지 않는 장비입니다.");
            return;
        }


        System.out.print("변경할 상태 : ");
        String e_Status = scan.next();


        boolean result
                = adminController.e_statusupdate(
                        e_No,
                        e_Status
                );


        if (result) {
            System.out.println("장비 상태 변경 성공");

        } else {
            System.out.println("장비 상태 변경 실패");
        }
    }


    // 8. 장비 보관함 변경
    public void e_lockerUpdate() {

        System.out.println();
        System.out.println("===== 장비 보관함 변경 =====");

        System.out.print("장비번호 : ");
        int e_No = scan.nextInt();


        if (!adminController.e_NoCheck(e_No)) {

            System.out.println("존재하지 않는 장비입니다.");
            return;
        }


        System.out.print("변경할 보관함 번호 : ");
        int l_No = scan.nextInt();


        if (!adminController.l_NoCheck(l_No)) {

            System.out.println("존재하지 않는 보관함입니다.");
            return;
        }


        boolean result
                = adminController.e_lockerupdate(
                        e_No,
                        l_No
                );


        if (result) {
            System.out.println("장비 보관함 변경 성공");

        } else {
            System.out.println("장비 보관함 변경 실패");
        }
    }


    // 9. 장비 삭제
    public void e_delete() {

        System.out.println();
        System.out.println("===== 장비 삭제 =====");

        System.out.print("삭제할 장비번호 : ");
        int e_No = scan.nextInt();


        if (!adminController.e_NoCheck(e_No)) {

            System.out.println("존재하지 않는 장비입니다.");
            return;
        }


        boolean result
                = adminController.e_delete(e_No);


        if (result) {
            System.out.println("장비 삭제 성공");

        } else {
            System.out.println("장비 삭제 실패");
        }
    }
}