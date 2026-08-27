package view;

import java.util.ArrayList;

import controller.AdminController;
import model.dto.UsersDTO;

public class AdminUserView {
    private AdminUserView(){}
    private static final AdminUserView instance = new AdminUserView();
    public static AdminUserView getInstance() { return instance; }

    private AdminController ac = AdminController.getInstance();


        public void run() {

        ArrayList<UsersDTO> list = ac.selectAllUsers();

        System.out.println();
        System.out.println("===== 전체 회원 조회 =====");

        for (UsersDTO dto : list) {
            System.out.println(dto);
        }
    }
}
