package viewer;

import controller.TheaterController;
import model.MovieDTO;
import model.TheaterDTO;
import model.UserDTO;
import utill.ScannerUtill;

import java.util.ArrayList;
import java.util.Scanner;

public class TheaterViewer {
    private Scanner SCANNER;
    private TheaterController theaterController;
    private UserViewer userViewer;
    private MovieViewer movieViewer;
    private UserDTO login;
    private ArrayList<TheaterDTO> list;
    private ScreeanInfoViewer screeanInfoViewer;

    public void setScreeanInfoViewer(ScreeanInfoViewer screeanInfoViewer) {
        this.screeanInfoViewer = screeanInfoViewer;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    public void setLogin(UserDTO login) {
        this.login = login;
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }


    public TheaterViewer(Scanner scanner) {
        SCANNER = scanner;
        theaterController = new TheaterController();
        list = theaterController.selcetAll();
    }

    public ArrayList<TheaterDTO> getTheaterList() {
        return new ArrayList<TheaterDTO>(list);
    }


    public void showTheater() {    // 극장목록보기
        // print 극장번호. 극장이름
        // 일반사용자
        //      1. 상세보기 2. 뒤로가기
        // 관리자
        //      1. 상세보기 2. 극장등록하기 3. 극장수정하기 4. 극장삭제하기 5. 뒤로가기
        list = theaterController.selcetAll();
        if (login.getId() == 1) {
//            for (TheaterDTO t : list) {
//                System.out.printf("%d. %s\n", t.getId(), t.getThaterName());
//            }
            String message = "1. 극장선택 2. 극장등록 3. 극장수정 4. 극장삭제 0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 4);
            if (userChoice == 1) {
                theaterSelect();
            } else if (userChoice == 2) {
                insert();
            } else if (userChoice == 3) {
                update();
            } else if (userChoice == 4) {
                delete();
            } else if (userChoice == 0) {
                userViewer.loginMenu();
            }
        } else {
            for (TheaterDTO t : list) {
                System.out.printf("%d. %s\n", t.getId(), t.getThaterName());
            }
            String message = "1. 극장선택 2. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 2);
            if (userChoice == 1) {
                theaterSelect();
            } else if (userChoice == 2) {
                userViewer.loginMenu();
            }
        }
    }

    public void theaterSelect() {  // 극장선택
        // print 극장번호, 극장이름
        // 일반사용자
        //      print 극장번호를 입력 또는 뒤로가기('0')
        list = theaterController.selcetAll();
        for (TheaterDTO t : list) {
            System.out.printf("%d. %s\n", t.getId(), t.getThaterName());
        }
        String message = "극장번호를 입력 또는 뒤로가기('0')";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        TheaterDTO t = new TheaterDTO(userChoice);

        while (!list.contains(t)) {
            if (userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "극장번호를 입력 또는 뒤로가기('0')";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
            t.setId(userChoice);
        }
        if (userChoice == 0) {
            userViewer.loginMenu();
        } else {
            theaterOne(userChoice);
        }

    }

    public void theaterOne(int id) { // 극장상세보기
        // print 극장이름, 극장위치, 극장번호
        // 1. 상영중인영화정보 2. 뒤로가기

        TheaterDTO t = theaterController.selectById(id);

        System.out.printf("극장이름: %s, 위치: %s, 번호: %s\n", t.getThaterName(), t.getLocation(), t.getPhoneNumber());
        String message = "1. 상영중인영화정보 0. 뒤로가기";
        int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 1);
        if (userChoice == 1) {
            screeanInfoViewer.showTheaterInfo(t);
        } else if (userChoice == 0) {
            theaterSelect();
        }
    }

    public void insert() {
        String message = "극장 이름을 입력해주세요.";
        String thaterName = ScannerUtill.nextLine(SCANNER, message);
        message = "극장 위치를 입력해주세요.";
        String location = ScannerUtill.nextLine(SCANNER, message);
        message = "극장 전화번호를 입력해주세요.";
        String phonNumber = ScannerUtill.nextLine(SCANNER, message);

        TheaterDTO t = new TheaterDTO();
        t.setThaterName(thaterName);
        t.setLocation(location);
        t.setPhoneNumber(phonNumber);
        theaterController.insert(t);
        showTheater();
    }

    public void update() {
        list = theaterController.selcetAll();
        for (TheaterDTO t : list) {
            System.out.printf("%d. %s\n", t.getId(), t.getThaterName());
        }
        String message = "수정할 극장번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new TheaterDTO(userChoice))) {
            if (userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "수정할 극장번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showTheater();
        } else {
            message = "새 극장 이름을 입력해주세요.";
            String theaterName = ScannerUtill.nextLine(SCANNER, message);
            message = "새 극장 위치를 입력해주세요.";
            String location = ScannerUtill.nextLine(SCANNER, message);
            message = "새 극장 번호를 입력해주세요.";
            String phoneNumber = ScannerUtill.nextLine(SCANNER, message);

            TheaterDTO t = new TheaterDTO(userChoice);
            t.setThaterName(theaterName);
            t.setLocation(location);
            t.setPhoneNumber(phoneNumber);
            theaterController.update(t);

            showTheater();

        }
    }

    public void delete() {
        list = theaterController.selcetAll();
        for (TheaterDTO t : list) {
            System.out.printf("%d. %s\n", t.getId(), t.getThaterName());
        }
        String message = "삭제할 극장번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new TheaterDTO(userChoice))) {
            if (userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "삭제할 극장번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showTheater();
        } else {
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtill.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("y")) {
                screeanInfoViewer.deleteAllById(new TheaterDTO(userChoice));
                theaterController.delete(userChoice);
                showTheater();

            } else {
                showTheater();
            }
        }
    }
    public void deleteAllById(int theaterId){
        ArrayList<TheaterDTO> list = new ArrayList<>();
        for(TheaterDTO t : list){
            if(t.getId() == theaterId){
                theaterController.delete(t.getId());
            }
        }
    }
}
