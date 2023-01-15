package viewer;

import controller.MovieController;
import model.MovieDTO;
import model.UserDTO;
import utill.ScannerUtill;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewer {
    private Scanner SCANNER;
    private MovieController movieController;
    private UserDTO login;
    private ArrayList<MovieDTO> list;

    public MovieViewer(Scanner scanner) {
        SCANNER = scanner;
        movieController = new MovieController();
        list = movieController.selcetAll();
    }
    public void setLogin(UserDTO login){
        this.login = login;
    }

    public void showMovie() {    // 영화목록보기
        // print 영화번호. 영화이름
        // 일반사용자
        //      1. 상세보기 2. 뒤로가기
        // 관리자
        //      1. 상세보기 2. 영화등록하기 3. 영화수정하기 4. 영화삭제하기 5. 뒤로가기
        if (login.getId() == 1) {
            for (MovieDTO m : list) {
                System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
            }
            String message = "1. 상세보기 2. 영화등록하기 3. 영화수정하기 4. 영화삭제하기 5. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 5);
            if (userChoice == 1) {
                movieSelect(userChoice);
            } else if (userChoice == 2) {

            } else if (userChoice == 3) {

            } else if (userChoice == 4) {

            } else if (userChoice == 5) {

            }
        } else {
            for (MovieDTO m : list) {
                System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
            }
            String message = "1. 상세보기 2. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 2);
            if (userChoice == 1) {
                movieSelect(userChoice);
            } else if (userChoice == 2) {

            }
        }
    }

    public void movieSelect(int id) {    // 상세보기할 영화번호선택
        // print 영화번호를 선택해주세요.
        String message = "영화번호를 선택해주세요.";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        if (list.isEmpty()) {
            System.out.println("상세보기할 영화가 없습니다.");
            showMovie();
        } else {
            while (!list.contains(userChoice)) {
                message = "잘못 입력하셨습니다.";
                userChoice = ScannerUtill.nextInt(SCANNER, message);
            }
            movieOne(userChoice);
        }

    }

    public void movieOne(int id ) {     // 영화상세보기
        // print 영화이름, 영화등급
        // print 줄거리
        // 1. 극장선택 2. 평점보기 3. 평론보기 4. 뒤로가기
        MovieDTO m = list.get(id);
        System.out.printf("영화이름: %s, 영화등급: %d", m.getMovieName(), m.getRank());
        System.out.printf("줄거리: %s", m.getContent());
        String message = "1. 극장선택 2. 평점보기 3. 평론보기 4. 뒤로가기";
        int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 4);
        if (userChoice == 1) {

        } else if (userChoice == 2) {

        } else if (userChoice == 3) {

        } else if (userChoice == 4) {

        }
    }

    public void insert() {   // 영화등록

    }

    public void update() {   // 영화수정

    }

    public void delete() {   // 영화삭제

    }
}
