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
        while( true ){
            try {
                System.out.println("┌──────────────────────────────────────────────┐");
                System.out.println("│               고장 / 파손 신고                │");
                System.out.println("├──────────────────────────────────────────────┤");
                System.out.println("│ 장비번호 : TB-002                             │");
                System.out.println("│ 장비명   : 태블릿                             │");
                System.out.println("│                                              │");
                System.out.println("│ 불량 내용 입력                                │");
                System.out.println("│ : 화면이 계속 깜빡거립니다.                    │");
                System.out.println("│                                              │");
                System.out.println("│ 1. 신고 및 반납                               │");
                System.out.println("│ 2. 취소                                      │");
                System.out.println("└──────────────────────────────────────────────┘");

                int ch = scan.nextInt();
                if( ch == 1 )
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

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