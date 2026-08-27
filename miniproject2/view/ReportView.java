package view;

import java.util.ArrayList;
import java.util.Scanner;
import controller.AdminController;
import controller.StudentController;
import model.dto.RentalDTO;
import model.dto.ReportDTO;

public class ReportView {
    
    private ReportView(){}
    private static final ReportView instance = new ReportView();
    public static ReportView getInstance() { return instance; }

    private AdminController ac = AdminController.getInstance();
    private StudentController sc = StudentController.getInstance();

    private Scanner scan = new Scanner(System.in);

    // [1] 신고 등록 화면 메서드
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
    // [2] 전체 신고내역 조회 화면
    public void reportFindAllView() {

    try {

        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                         전체 신고내역 조회                                 │");
        System.out.println("└──────────────────────────────────────────────────────────────────────────┘");

        // AdminController에서 전체 신고 목록 받아오기
        var list = ac.reportFindAll();

        if(list.size() == 0) {
            System.out.println("[안내] 등록된 신고가 없습니다.");
            return;
        }

        for(ReportDTO reportDTO : list) {
            System.out.println("신고번호   : " + reportDTO.getReportId());
            System.out.println("대여번호   : " + reportDTO.getRNo());
            System.out.println("신고유형   : " + reportDTO.getReportType());
            System.out.println("불량내용   : " + reportDTO.getDescription());
            System.out.println("신고일시   : " + reportDTO.getrReturnDate());
            System.out.println("처리상태   : " + reportDTO.getStatus());
            System.out.println("--------------------------------------------------");
        }

    } catch(Exception e) {
        System.out.println(e);
    }
}
    // [3] 신고 상세 조회 화면
    public void reportFindView() {

    try {
        System.out.print("조회할 신고번호 입력 >> ");
        int reportId = scan.nextInt();

        ReportDTO reportDTO = ac.reportFind(reportId);

        if(reportDTO == null) {
            System.out.println("[안내] 해당 신고가 없습니다.");
            return;
        }
        System.out.println();
        System.out.println("신고번호 : " + reportDTO.getReportId());
        System.out.println("대여번호 : " + reportDTO.getRNo());
        System.out.println("신고유형 : " + reportDTO.getReportType());
        System.out.println("불량내용 : " + reportDTO.getDescription());
        System.out.println("신고일시 : " + reportDTO.getrReturnDate());
        System.out.println("처리상태 : " + reportDTO.getStatus());

    } catch(Exception e) {
        System.out.println(e);
        scan.nextLine();
    }
}
    // [4] 신고 유형별 조회 화면
    public void reportTypeFindView() {
    try {
        System.out.println("신고 유형 선택");
        System.out.println("1. 고장");
        System.out.println("2. 파손");
        System.out.print("선택 >> ");

        int ch = scan.nextInt();

        String reportType = "";

        if(ch == 1) {
            reportType = "고장";
        } else if(ch == 2) {
            reportType = "파손";
        } else {
            System.out.println("[안내] 잘못된 번호입니다.");
            return;
        }
        ArrayList<ReportDTO> list = ac.reportTypeFind(reportType);

        if(list.size() == 0) {
            System.out.println("[안내] 해당 유형의 신고가 없습니다.");
            return;
        }
        for(ReportDTO reportDTO : list) {
            System.out.println("----------------------------------");
            System.out.println("신고번호 : " + reportDTO.getReportId());
            System.out.println("대여번호 : " + reportDTO.getRNo());
            System.out.println("신고유형 : " + reportDTO.getReportType());
            System.out.println("불량내용 : " + reportDTO.getDescription());
            System.out.println("신고일시 : " + reportDTO.getrReturnDate());
            System.out.println("처리상태 : " + reportDTO.getStatus());
        }

    } catch(Exception e) {
        System.out.println(e);
        scan.nextLine();
    }
}
    // [5] 처리 상태별 조회 화면
    public void reportStatusFindView() {
    try {
        System.out.println("처리 상태 선택");
        System.out.println("1. 접수");
        System.out.println("2. 점검중");
        System.out.println("3. 수리중");
        System.out.println("4. 처리완료");
        System.out.print("선택 >> ");

        int ch = scan.nextInt();

        String status = "";

        if(ch == 1) {
            status = "접수";
        } else if(ch == 2) {
            status = "점검중";
        } else if(ch == 3) {
            status = "수리중";
        }else if(ch == 4) {
            status = "처리완료";
        } else {
            System.out.println("[안내] 잘못된 번호입니다.");
            return;
        }

        ArrayList<ReportDTO> list = ac.reportStatusFind(status);

        if(list.size() == 0) {
            System.out.println("[안내] 해당 상태의 신고가 없습니다.");
            return;
        }
        for(ReportDTO reportDTO : list) {
            System.out.println("----------------------------------");
            System.out.println("신고번호 : " + reportDTO.getReportId());
            System.out.println("대여번호 : " + reportDTO.getRNo());
            System.out.println("신고유형 : " + reportDTO.getReportType());
            System.out.println("불량내용 : " + reportDTO.getDescription());
            System.out.println("신고일시 : " + reportDTO.getrReturnDate());
            System.out.println("처리상태 : " + reportDTO.getStatus());
        }
    } catch(Exception e) {
        System.out.println(e);
        scan.nextLine();
    }
}
    // [6] 신고 처리상태 변경 화면
    public void reportStatusUpdateView() {
    try {
        System.out.print("상태 변경할 신고번호 입력 >> ");
        int reportId = scan.nextInt();

        System.out.println("변경할 처리 상태 선택");
        System.out.println("1. 접수");
        System.out.println("2. 점검중");
        System.out.println("3. 수리중");
        System.out.println("4. 처리완료");
        System.out.print("선택 >> ");

        int ch = scan.nextInt();

        String status = "";

        if(ch == 1) {
            status = "접수";
        } else if(ch == 2) {
            status = "점검중";
        } else if(ch == 3) {
            status = "수리중";
        } else if(ch == 4) {
            status = "처리완료";
        } else {
            System.out.println("[안내] 잘못된 번호입니다.");
            return;
        }

        boolean result = ac.reportStatusUpdate(reportId, status);

        if(result) {
            System.out.println("[안내] 처리상태가 변경되었습니다.");
        } else {
            System.out.println("[안내] 처리상태 변경에 실패했습니다.");
        }

    } catch(Exception e) {
        System.out.println(e);
        scan.nextLine();
    }
}
// [7] 장비 최근 이용내역 조회 화면
public void recentRentalFindView() {
    try {
        System.out.print("조회할 장비번호 입력 >> ");
        int eNo = scan.nextInt();

        RentalDTO rentalDTO = ac.recentRentalFind(eNo);

        if(rentalDTO == null) {
            System.out.println("[안내] 해당 장비의 이용내역이 없습니다.");
            return;
        }

        System.out.println();
        System.out.println("최근 이용자 번호 : " + rentalDTO.getU_no());
        System.out.println("대여일          : " + rentalDTO.getR_date());
        System.out.println("반납일          : " + rentalDTO.getR_return_date());

    } catch(Exception e) {
        System.out.println(e);
        scan.nextLine();
    }
}
public void reportAdminMenu() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'reportAdminMenu'");
}
} // class end

