import view.ReportView;

public class AppStart {
    public static void main(String[] args) {

        ReportView.getInstance()
                  .reportAddView(1, 2, "태블릿");

    }
}