package view;

import java.util.Scanner;
import controller.AdminController;
import controller.StudentController;
import model.dto.ReportDTO;

public class ReportView {
    
    private ReportView(){}
    private static final ReportView instance = new ReportView();
    public static ReportView getInstance() { return instance; }

    private AdminController ac = AdminController.getInstance();
    private StudentController sc = StudentController.getInstance();

    private Scanner scan = new Scanner(System.in);

    // 신고 등록 화면 메서드
    public void reportAddView(int rNo) {

        // 1. "고장 / 파손 신고" 화면 출력

        // 2. 신고 유형 선택
        // 1 = 고장
        // 2 = 파손

        // 3. 신고내용 입력

        // 4. ReportDTO 생성

        // 5. rNo / reportType / description setter

        // 6. StudentController.reportAdd() 호출

        // 7. true/false에 따라
        // "신고 등록 성공" / "실패" 출력
    }
}