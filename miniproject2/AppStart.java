import view.AdminView;
import view.LockerStudentView;
import view.LoginView;

public class AppStart {
    public static void main(String[] args) {
        System.out.println("시작");
        LoginView.getInstance().run();
    }
}