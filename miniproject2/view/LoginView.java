package view;

import java.util.Scanner;

import controller.LoginController;
import model.dto.UsersDTO;

import java.util.Scanner;

import controller.LoginController;
import model.dto.UsersDTO;

public class LoginView {
    private LoginView(){}
    private static final LoginView instance = new LoginView();
    public static LoginView getInstance(){ return instance; }

    private LoginController lc = LoginController.getInstance();

    private Scanner scan = new Scanner(System.in);

    public void run(){
            while (true) {
                try{
                    System.out.println("1.회원가입 2.로그인 3.종료 :");
                    int ch = scan.nextInt();
                    if( ch == 1 ){ 
                        signup(); 
                    } else if ( ch == 2 ){
                        login();
                    } else if ( ch == 3 ){
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                
                    }catch( Exception e ){ 
                        System.out.println("입력 오류입니다. 다시 시도하세요.");
                        scan.next();
                    }
            }
    }
    


    public void signup(){
        System.out.println("학번"); String studentid = scan.next();
        System.out.println("비밀번호"); String pwd = scan.next();
        System.out.println("이름"); String name = scan.next();
        System.out.println("연락처"); String phone = scan.next();

        UsersDTO usersDTO = new UsersDTO(0, studentid, pwd, name, phone, "user");
        boolean result = lc.signup( usersDTO );

        if (result) {
            System.out.println("[안내] 회원가입이 정상적으로 완료되었습니다.");
        } else {
            System.out.println("[안내] 회원가입에 실패했습니다.");
        }
    }

    public void login(){
        System.out.println("학번"); String studentid = scan.next();
        System.out.println("비밀번호"); String pwd = scan.next();

        boolean result = lc.login(studentid, pwd);

        if (result){
            UsersDTO loginUsers = lc.getLoginUser();
            System.out.println("[안내] 로그인에 성공했습니다.");
            System.out.println("[안내] " + loginUsers.getU_name() + "님 환영합니다.");

            if ( loginUsers.getU_grade().equals("admin")) {
            AdminView.getInstance().run();
            }
            else {
            StudentView.getInstance().run();
            }

        } else {
            System.out.println("학번 또는 비밀번호가 일치하지 않습니다.");
        }
            
        
    }
}
