package viewer;

import controller.MovieController;
import model.MovieDTO;
import model.TheaterDTO;
import model.UserDTO;
import utill.ScannerUtill;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewer {
    private Scanner SCANNER;
    private MovieController movieController;
    private UserDTO login;
    private ArrayList<MovieDTO> list;
    private UserViewer userViewer;
    private TheaterViewer theaterViewer;
    private ReviewViewer reviewViewer;
    private ScreeanInfoViewer screeanInfoViewer;
    private TheaterDTO theaterDTO;

    public MovieViewer(Scanner scanner) {
        SCANNER = scanner;
        movieController = new MovieController();
        list = movieController.selcetAll();
        theaterDTO = null;
    }

    public ArrayList<MovieDTO> getMovieList() {
        return new ArrayList<MovieDTO>(list);
    }

    public void setScreeanInfoViewer(ScreeanInfoViewer screeanInfoViewer) {
        this.screeanInfoViewer = screeanInfoViewer;
    }

    public void setTheaterDTO(TheaterDTO theaterDTO) {
        this.theaterDTO = theaterDTO;
    }

    public void setLogin(UserDTO login) {
        this.login = login;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    public void setTheaterViewer(TheaterViewer theaterViewer) {
        this.theaterViewer = theaterViewer;
    }

    public void setReviewViewer(ReviewViewer reviewViewer) {
        this.reviewViewer = reviewViewer;
    }

    public void showMovie() {    // 영화목록보기
        // print 영화번호. 영화이름
        // 일반사용자
        //      1. 상세보기 2. 뒤로가기
        // 관리자
        //      1. 상세보기 2. 영화등록하기 3. 영화수정하기 4. 영화삭제하기 5. 뒤로가기
        list = movieController.selcetAll();
        if (login.getId() == 1) {
//            for (MovieDTO m : list) {
//                System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
//            }
            String message = "1. 영화선택 2. 영화등록 3. 영화수정 4. 영화삭제 0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 4);
            if (userChoice == 1) {
                movieSelect(userChoice);
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
            for (MovieDTO m : list) {
                System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
            }
            String message = "1. 영화선택 2. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 1, 2);
            if (userChoice == 1) {
                movieSelect(userChoice);
            } else if (userChoice == 2) {
                userViewer.loginMenu();
            }
        }
    }

    public void movieSelect(int id) {    // 상세보기할 영화번호선택
        // print 영화번호를 선택해주세요.
        list = movieController.selcetAll();
        for (MovieDTO m : list) {
            System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
        }
        String message = "영화번호 또는 뒤로가기('0')를 입력해주세요.";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        MovieDTO m = new MovieDTO(userChoice);
        if (list.isEmpty()) {
            System.out.println("상세보기할 영화가 없습니다.");
            showMovie();
        } else {
            while (!list.contains(m)) {
                if (userChoice == 0) break;
                message = "영화번호 또는 뒤로가기('0')를 입력해주세요.";
                userChoice = ScannerUtill.nextInt(SCANNER, message);
                m.setId(userChoice);
            }
            if (userChoice == 0) {
                showMovie();
            } else {
                movieOne(userChoice);
            }
        }

    }

    public void movieOne(int id) {     // 영화상세보기
        // print 영화이름, 영화등급
        // print 줄거리
        // 1. 상영정보보기 2. 평점보기 3. 평론보기 4. 뒤로가기
        MovieDTO m = list.get(list.indexOf(new MovieDTO(id)));
        System.out.printf("영화이름: %s, 영화등급: %d\n", m.getMovieName(), m.getRank());
        System.out.printf("줄거리: %s\n", m.getContent());
        String message = "1. 상영정보보기 2. 평점보기 3. 평론보기 0. 뒤로가기";
        int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 3);
        if (userChoice == 1) {
//            theaterViewer.setMovieDTO(m);
//            theaterViewer.TheaterSelect();   // 극장선택
            screeanInfoViewer.showMovieInfo(m);
        } else if (userChoice == 2) {
            reviewViewer.showRank(m);
        } else if (userChoice == 3) {
            reviewViewer.showReview(m);
        } else if (userChoice == 0) {
            showMovie();
        }
    }

    public void insert() {   // 영화등록
        String message = "영화 제목을 입력해주세요.";
        String moviename = ScannerUtill.nextLine(SCANNER, message);
        message = "영화 줄거리를 입력해주세요.";
        String content = ScannerUtill.nextLine(SCANNER, message);
        System.out.println("1. 청불 2. 15세관람가 3. 전체관람가");
        message = "영화 등급을 입력해주세요.";
        int rank = ScannerUtill.nextInt(SCANNER, message, 1, 3);

        MovieDTO m = new MovieDTO();
        m.setMovieName(moviename);
        m.setContent(content);
        m.setRank(rank);
        movieController.insert(m);
        showMovie();
    }


    public void update() {   // 영화수정
        list = movieController.selcetAll();
        for (MovieDTO m : list) {
            System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
        }
        String message = "수정할 영화번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new MovieDTO(userChoice))) {
            if(userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "수정할 영화번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showMovie();
        }else{
            message = "새 영화 제목을 입력해주세요.";
            String moviename = ScannerUtill.nextLine(SCANNER, message);
            message = "새 영화 줄거리를 입력해주세요.";
            String content = ScannerUtill.nextLine(SCANNER, message);
            message = "새 영화 등급을 입력해주세요.";
            int rank = ScannerUtill.nextInt(SCANNER, message, 1, 3);

            MovieDTO m = new MovieDTO(userChoice);
            m.setMovieName(moviename);
            m.setContent(content);
            m.setRank(rank);
            movieController.update(m);

            showMovie();
        }
    }

    public void delete() {   // 영화삭제
        list = movieController.selcetAll();
        for (MovieDTO m : list) {
            System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
        }
        String message = "삭제할 영화번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new MovieDTO(userChoice))) {
            if(userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "삭제할 영화번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showMovie();
        }else{
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtill.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("y")) {
                screeanInfoViewer.deleteAllById(new MovieDTO(userChoice));
                movieController.delete(userChoice);
            }
            showMovie();
        }
    }
    public void deleteAllById(int movieId){
        ArrayList<MovieDTO> list = new ArrayList<>();
        for(MovieDTO m : list){
            if(m.getId() == movieId){
                movieController.delete(m.getId());
            }
        }
    }
}
