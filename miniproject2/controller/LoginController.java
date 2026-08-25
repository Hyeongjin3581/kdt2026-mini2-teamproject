package controller;

import model.dao.UsersDAO;
import model.dto.UsersDTO;

public class LoginController {
    private LoginController(){}
    private static final LoginController instance = new LoginController();
    public static LoginController getInstance(){ return instance; }
    
    private UsersDAO ud = UsersDAO.getInstance();

    // 로그인 회원 저장
    private UsersDTO loginUser = null;

    public boolean signup( UsersDTO usersDto ){
        boolean result = ud.signup(usersDto);
        return result;
    }

    // 로그인
    public boolean login(String studentId, String pwd) {
    UsersDTO user = ud.login(studentId, pwd);
    if (user == null) {
        return false;
    }
    loginUser = user;
    return true;
    }

    // 현재 로그인한 회원 정보 확인
    public UsersDTO getLoginUser() {
        return loginUser;
    }

    // 로그아웃
    public void logout() {
        loginUser = null;
    }
}
    