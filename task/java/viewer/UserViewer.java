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
    private MovieViewer movieViewer;
    private TheaterViewer theaterViewer;
    private ReviewViewer reviewViewer;
    private ScreeanInfoViewer screeanInfoViewer;

    public UserViewer(Scanner scanner) {
        userController = new UserController();
        SCANNER = scanner;
    }

    public void setLogInAll() {  // 모든뷰 로그인 최신화, 리뷰뷰 유저list
        movieViewer.setLogin(logIn);
        theaterViewer.setLogin(logIn);
        screeanInfoViewer.setLogin(logIn);
        reviewViewer.setLogin(logIn);
        reviewViewer.setUserList(userController.selectAll());
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public void setTheaterViewer(TheaterViewer theaterViewer) {
        this.theaterViewer = theaterViewer;
    }

    public void setScreeanInfoViewer(ScreeanInfoViewer screeanInfoViewer) {
        this.screeanInfoViewer = screeanInfoViewer;
    }

    public void setReviewViewer(ReviewViewer reviewViewer) {
        this.reviewViewer = reviewViewer;
    }

    public void showLoginPage() {   // 로그인 페이지
        while (true) {
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
        ArrayList<UserDTO> list = userController.selectAll();
        String message = "사용하실 아이디를 입력하거나 뒤로가실려면 \'x\'를 입력해주세요 ";
        String username = ScannerUtill.nextLine(SCANNER, message);
        String password = "";
        String nickname = "";
        boolean overlap = false;
        if (username.equalsIgnoreCase("x")) {
        } else {
            if (!overlap) {
                for (UserDTO u : list) {
                    if (u.getUsername().equals(username)) {
                        System.out.println("이미 사용한 아이디입니다.");
                        overlap = true;
                        break;
                    }
                }
            }
            if (!overlap) {
                message = "사용하실 비밀번호를 입력해주세요.";
                password = ScannerUtill.nextLine(SCANNER, message);

                message = "사용하실 닉네임을 입력해주세요.";
                nickname = ScannerUtill.nextLine(SCANNER, message);

                UserDTO u = new UserDTO();
                u.setUsername(username);
                u.setPassword(password);
                u.setNickname(nickname);
                userController.insert(u);
            }
        }
    }


    public void loginMenu() {  // 로그인 메뉴
        if (logIn != null) {
            setLogInAll();
            if (logIn.getRank() == 1) {
                String message = "1. 영화목록보기 2. 극장목록보기 3. 회원등급수정 4. 회원정보보기 0. 로그아웃";
                int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 4);
                if (userChoice == 1) {  // 영화목록보기
                    screeanInfoViewer.setTheaterList(theaterViewer.getTheaterList());
                    screeanInfoViewer.setMovieList(movieViewer.getMovieList());
                    movieViewer.showMovie();
                } else if (userChoice == 2) {
                    screeanInfoViewer.setMovieList(movieViewer.getMovieList());
                    screeanInfoViewer.setTheaterList(theaterViewer.getTheaterList());
                    theaterViewer.showTheater();
                } else if (userChoice == 3) {
                    showRank();   // 등업신청목록보기
                } else if (userChoice == 4) {
                    userInfo();
                } else if (userChoice == 0) {

                }
            } else {
                String message = "1. 영화목록보기 2. 극장목록보기 3. 회원정보보기 4. 등업신청 0. 로그아웃";
                int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 4);
                if (userChoice == 1) {
                    screeanInfoViewer.setTheaterList(theaterViewer.getTheaterList());
                    screeanInfoViewer.setMovieList(movieViewer.getMovieList());
                    movieViewer.showMovie();
                } else if (userChoice == 2) {
                    screeanInfoViewer.setMovieList(movieViewer.getMovieList());
                    screeanInfoViewer.setTheaterList(theaterViewer.getTheaterList());
                    theaterViewer.showTheater();
                } else if (userChoice == 3) {
                    userInfo();
                } else if (userChoice == 4) {
                    rankUpPage();
                } else if (userChoice == 0) {
                    System.out.println("로그아웃 되었습니다.");
                }
            }
        }
    }

    private void showRank() {  // 등업신청 및 전체등급변경 선택페이지
        String message = "1. 등업신청목록보기 2. 전체회원등급변경 0. 뒤로가기";
        int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 2);
        if (userChoice == 1) {
            showRankUp();
        } else if (userChoice == 2) {
            showRankChange();
        } else if (userChoice == 0) {
            loginMenu();
        }
    }

    private void showRankChange() {  // 전체회원등급변경
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
        if (u.getRank() == 1) {
            System.out.println("관리자의 등급은 변경할수없습니다.");
            showRankChange();
        } else {
            String message = "2. 평론가 3. 일반회원";
            u.setRank((ScannerUtill.nextInt(SCANNER, message, 2, 3)));
            userController.update(u);
            reviewViewer.realTimeRank(u);   // 등업 최신화
            showRankChange();
        }
    }

    public void showRankUp() {   // 등업목록
        ArrayList<UserDTO> rankUpList = userController.getRankUpList();
        if (rankUpList.isEmpty()) {
            System.out.println("등업목록이 비어있습니다.");
            showRank();
        } else {
            for (UserDTO u : rankUpList) {
                System.out.printf("%d. %s 등급: %d\n", u.getId(), u.getNickname(), u.getRank());
            }
            String message = "등업할 회원번호를 선택하시거나 뒤로가시려면 '0'을 입력해주세요";
            int userChoice = ScannerUtill.nextInt(SCANNER, message);
            if (userChoice == 0) showRank();
            else {
                while (!rankUpList.contains(new UserDTO(userChoice))) {
                    if (userChoice == 0) break;
                    message = "잘못된 회원번호입니다.";
                    userChoice = ScannerUtill.nextInt(SCANNER, message);
                }
                UserDTO u = userController.getRankUpById(userChoice);
                userController.update(u);
                reviewViewer.realTimeRank(u);   // 등업 최신화
                userController.deleteRankUp(u.getId()); // 해당등업 list에서 삭제
                showRank();
            }
            if (userChoice == 0) showRank();
        }
    }

    private void userInfo() {    // 회원정보
        if (logIn != null) {
            String message = "1. 수정 2. 탈퇴 0.뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                update();
            } else if (userChoice == 2) {
                delete();
            }else if (userChoice == 0){
                loginMenu();
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
        if (logIn.getPassword().equals(password)) {
            userController.update(u);
            logIn = u;
            System.out.println("변경되셨습니다.");
        } else {
            System.out.println("변경실패. 틀린 비밀번호입니다.");
        }
        loginMenu();
    }

    private void delete() {  // 회원탈퇴
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtill.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("y")) {
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtill.nextLine(SCANNER, message);
            if (logIn.getPassword().equalsIgnoreCase(password)) {
                reviewViewer.realTimeDelete(logIn.getId()); // 회원번호에 맞는 리뷰데이터 전부 삭제
                userController.delete(logIn.getId());   // 회원 삭제
                userController.deleteRankUp(logIn.getId());
                logIn = null;
            }
            showLoginPage();
        } else {
            loginMenu();
        }
    }

    public void rankUpPage() {   // 등업신청 페이지
        ArrayList<UserDTO> rankList = userController.getRankUpList();
        if (rankList.contains(logIn)) {
            System.out.println("이미 등업신청이 완료되었습니다.");
            loginMenu();
        } else {
            if (logIn.getRank() == 2) {
                UserDTO u = new UserDTO(logIn);
                u.setRank(1);
                System.out.println("관리자로 등업신청이 완료되었습니다.");
                userController.insertRankUp(u);
            } else if (logIn.getRank() == 3) {
                UserDTO u = new UserDTO(logIn);
                u.setRank(2);
                System.out.println("평론가로 등업신청이 완료되었습니다.");
                userController.insertRankUp(u);
            }
            loginMenu();
        }
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
        } else {
            return true;
        }
    }

    public ArrayList<UserDTO> getUserList() {
        return userController.selectAll();
    }
}




















