package viewer;

import controller.UserController;
import model.UserDTO;
import utill.ScannerUtill;

import java.util.ArrayList;
import java.util.Scanner;

public class UserViewer {
    private Scanner SCANNER;
    private UserController userController;
    private UserDTO logIn;

    public UserViewer(Scanner scanner) {
        userController = new UserController();
        SCANNER = scanner;
    }

    public void showLoginPage() {   // 로그인 페이지
        while(true) {
            String message = "1. 로그인 2. 회원가입 3. 종료";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                if (auth()) {
                    loginMenu();
                }
            } else if (userChoice == 2) {
                register(); // 회원가입
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }

    }

    private void register() {    // 회원가입
        String message = "사용하실 아이디를 입력하거나 뒤로가실려면 \'x\'를 입력해주세요 ";
        String username = ScannerUtill.nextLine(SCANNER, message);
        if (username.equalsIgnoreCase("x")) {
            showLoginPage();
        } else {
            message = "사용하실 비밀번호를 입력해주세요.";
            String password = ScannerUtill.nextLine(SCANNER, message);
            message = "사용하실 닉네임을 입력해주세요.";
            String nickname = ScannerUtill.nextLine(SCANNER, message);

            UserDTO u = new UserDTO();
            u.setUsername(username);
            u.setPassword(password);
            u.setNickname(nickname);
            userController.insert(u);
            showLoginPage();
        }

    }

    private void loginMenu() {  // 로그인 메뉴
        if(logIn!=null){
            if(logIn.getRank()==1) {
                String message = "1. 영화목록보기 2. 극장목록보기 3. 회원등급변경 4. 회원정보보기 5. 로그아웃";
                int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 5);
                if (userChoice == 1) {
                } else if (userChoice == 2) {
                } else if (userChoice == 3) {
                    showRankChange();
                } else if (userChoice == 4) {
                    userInfo();
                } else if (userChoice == 5) {
                }
            }else{
                String message = "1. 영화목록보기 2. 극장목록보기 3. 회원정보보기 4. 로그아웃";
                int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 4);
                if (userChoice == 1) {
                } else if (userChoice == 2) {
                } else if (userChoice == 3) {
                    userInfo();
                } else if (userChoice == 4) {
                    System.out.println("로그아웃 되었습니다.");
                }
            }
        }
    }

    private void showRankChange() {  // 회원등급변경
        ArrayList<UserDTO> list = userController.selectAll();
        System.out.println("------------------------------------");
        for (UserDTO u : list) {
            System.out.printf("%d. <%s> 등급: %d\n", u.getId(), u.getNickname(), u.getRank());
        }
        System.out.println("------------------------------------");
        String message = "등급변경하실 회원을 선택하거나 뒤로가실려면 0을 눌러주세요.";
        int userId = ScannerUtill.nextInt(SCANNER, message);
        UserDTO u = new UserDTO(userId);
        while (userId != 0 && !list.contains(u)) {
            System.out.println("잘못 입력하셨습니다.");
            message = "등급변경하실 회원을 선택하거나 뒤로가실려면 0을 눌러주세요.";
            userId = ScannerUtill.nextInt(SCANNER, message);
            u.setId(userId);
        }
        if (userId == 0) {
            loginMenu();
        } else {
            rankChange(userId);
        }
    }

    private void rankChange(int id) {
        UserDTO u = userController.selectById(id);
        if (u.getRank() == 1){
            System.out.println("관리자의 등급은 변경할수없습니다.");
            showRankChange();
        }else {
            String message = "2. 일반회원 3. 평론가";
            u.setRank((ScannerUtill.nextInt(SCANNER, message, 2, 3)));
            userController.update(u);
            showRankChange();
        }
    }

    private void userInfo() {    // 회원정보
        if(logIn!= null) {
            String message = "1. 수정 2. 탈퇴 3.뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                update();
            } else if (userChoice == 2) {
                delete();
            }
        }
    }

    private void update() {  // 회원정보수정
        UserDTO u = new UserDTO(logIn);
        String message = "변경하실 비밀번호를 설정해주세요";
        String newPassword = ScannerUtill.nextLine(SCANNER, message);
        message = "변경하실 닉네임을 설정해주세요.";
        String newNickname = ScannerUtill.nextLine(SCANNER, message);
        message = "기존 비밀번호를 입력해주세요.";
        String password = ScannerUtill.nextLine(SCANNER, message);
        u.setPassword(newPassword);
        u.setNickname(newNickname);
        if(logIn.getPassword().equals(password)){
            userController.update(u);
            logIn = u;
            System.out.println("변경되셨습니다.");
        }else{
            System.out.println("변경실패. 틀린 비밀번호입니다.");
        }
        loginMenu();
    }

    private void delete() {  // 회원탈퇴
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtill.nextLine(SCANNER, message);
        if(yesNo.equalsIgnoreCase("y")){
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtill.nextLine(SCANNER, message);
            if(logIn.getPassword().equalsIgnoreCase(password)){
                userController.delete(logIn.getId());
                logIn = null;
            }
        }
        loginMenu();
    }

    private boolean auth() { // 인증
        String message = "아이디를 입력해주세요.";
        String id = ScannerUtill.nextLine(SCANNER, message);
        message = "비밀번호를 입력해주세요";
        String password = ScannerUtill.nextLine(SCANNER, message);

        logIn = userController.auth(id, password);

        if (logIn == null) {
            System.out.println("로그인 정보가 일치하지 않습니다.");
            return false;
        }else{
            return true;
        }
    }
}




















