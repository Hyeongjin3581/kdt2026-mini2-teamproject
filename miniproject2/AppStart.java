import view.AdminView;
import view.LockerStudentView;
import view.LoginView;
import view.StudentView;

public class AppStart {
    public static void main(String[] args) {
        System.out.println("시작");
        StudentView.getInstance().run();

    }
}