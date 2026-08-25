package controller;

import model.dao.UsersDAO;
import model.dto.UsersDTO;

public class LoginController {
    private LoginController(){}
    private static final LoginController instance = new LoginController();
    public static LoginController getInstance(){ return instance; }
    
    private UsersDAO ud = UsersDAO.getInstance();

    public boolean signup( UsersDTO usersDto ){
        boolean result = ud.signup(usersDto);
        return result;
    }
}
    