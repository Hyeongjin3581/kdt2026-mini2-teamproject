package view;

public class LockerView {
    private LockerView(){}
    private static final LockerView instance = new LockerView();
    public static LockerView getInstance() { return instance; }
}
