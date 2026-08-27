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
public void reportAddView(int rNo, int eNo, String eName) {

    while(true) {

        try {
            System.out.println("┌──────────────────────────────────────────────┐");
            System.out.println("│               고장 / 파손 신고                │");
            System.out.println("├──────────────────────────────────────────────┤");
            System.out.println("│ 장비번호 : " + eNo);
            System.out.println("│ 장비명   : " + eName);
            System.out.println("│                                              │");
            System.out.println("│ 신고 유형 선택                               │");
            System.out.println("│ 1. 고장                                      │");
            System.out.println("│ 2. 파손                                      │");
            System.out.println("└──────────────────────────────────────────────┘");
            System.out.print("신고 유형 선택 >> ");

            int typeCh = scan.nextInt();
            scan.nextLine(); // nextInt() 뒤에 남은 엔터 제거

            String reportType = "";

            if(typeCh == 1) {
                reportType = "고장";
            } else if(typeCh == 2) {
                reportType = "파손";
            } else {
                System.out.println("[안내] 잘못된 번호입니다.");
                continue;
            }

            System.out.print("불량 내용 입력 >> ");
            String description = scan.nextLine();

            System.out.println();
            System.out.println("1. 신고 및 반납");
            System.out.println("2. 취소");
            System.out.print("메뉴 선택 >> ");

            int ch = scan.nextInt();

            if(ch == 1) {

                ReportDTO reportDTO = new ReportDTO();

                reportDTO.setRNo(rNo);
                reportDTO.setReportType(reportType);
                reportDTO.setDescription(description);

                boolean result = sc.reportAdd(reportDTO);

                if(result) {
                    System.out.println("[안내] 신고가 정상적으로 등록되었습니다.");
                    break;
                } else {
                    System.out.println("[안내] 신고 등록에 실패했습니다.");
                }
            }

            if(ch == 2) {
                System.out.println("[안내] 신고를 취소합니다.");
                break;
            }

        } catch(Exception e) {
            System.out.println(e);
            scan.nextLine();
        }
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
