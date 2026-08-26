package view;

public class StudentView {
    private StudentView(){};
    private static final StudentView instance = new StudentView();
    public static StudentView getInstance() { return instance; }
}
