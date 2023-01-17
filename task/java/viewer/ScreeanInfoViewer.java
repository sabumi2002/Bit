package viewer;

import controller.MovieController;
import controller.ScreenInfoController;
import model.*;
import utill.ScannerUtill;

import java.util.ArrayList;
import java.util.Scanner;




public class ScreeanInfoViewer {
    private Scanner SCANNER;
    private ScreenInfoController screenInfoController;
    private UserDTO login;
    private TheaterViewer theaterViewer;
    private MovieViewer movieViewer;
    private ArrayList<ScreenInfoDTO> list;
    private ArrayList<MovieDTO> movieList;
    private ArrayList<TheaterDTO> theaterList;

    public ScreeanInfoViewer(Scanner scanner) {
        SCANNER = scanner;
        screenInfoController = new ScreenInfoController();
        list = screenInfoController.selcetAll();
    }

    public void setLogin(UserDTO login) {
        this.login = login;
    }

    public void setTheaterViewer(TheaterViewer theaterViewer) {
        this.theaterViewer = theaterViewer;
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public void setMovieList(ArrayList<MovieDTO> movieList) {
        this.movieList = movieList;
    }

    public void setTheaterList(ArrayList<TheaterDTO> theaterList) {
        this.theaterList = theaterList;
    }

    public void showTheaterInfo(TheaterDTO t) {  // 극장 > 상영정보
        // 영화이름, 극장이름, 상영시간
        // 일반사용자
        //      0. 뒤로가기
        // 관리자
        //      1. 등록 2. 수정 3. 삭제 0. 뒤로가기
        list = screenInfoController.selcetAll();
        movieList = movieViewer.getMovieList();
        ArrayList<ScreenInfoDTO> temp = new ArrayList<>();
        for (ScreenInfoDTO s : list) {
            if (s.getTheaterId() == t.getId()) {    // 해당 상영정보에서 극장번호가 같으면
                temp.add(s);
                MovieDTO m = new MovieDTO(s.getMovieId());
                int index = movieList.indexOf(m);
                if(index != -1) {
                    t = theaterList.get(index);                                                 // 에러남
                    System.out.printf("%d. 영화: %s, 극장: %s, 시간: %s\n", s.getId(), m.getMovieName(), t.getThaterName(), s.getRunningTime());
                }
            }
        }
        list = temp;

        if (login.getId() == 1) {
            String message = "1. 등록 2. 수정 3. 삭제 0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 3);
            if (userChoice == 1) {
                insert(t);
            } else if (userChoice == 2) {
                update(t);
            } else if (userChoice == 3) {
                delete(t);
            } else if (userChoice == 0) { // 뒤로가기
                theaterViewer.theaterOne(t.getId());
            }
        } else {
            String message = "0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 0);
            if (userChoice == 0) { // 뒤로가기
                theaterViewer.theaterOne(t.getId());
            }

        }
    }

    public void showMovieInfo(MovieDTO m) { // 영화 > 상영정보
        // 영화이름, 극장이름, 상영시간
        // 일반사용자
        //      0. 뒤로가기
        // 관리자
        //      1. 등록 2. 수정 3. 삭제 0. 뒤로가기
        movieList = movieViewer.getMovieList();
        list = screenInfoController.selcetAll();
        ArrayList<ScreenInfoDTO> temp = new ArrayList<>();
        for (ScreenInfoDTO s : list) {
            if (s.getMovieId() == m.getId()) {
                temp.add(s);
                TheaterDTO t = new TheaterDTO(s.getTheaterId());
                int index = theaterList.indexOf(t);
                if(index != -1) {
                    t = theaterList.get(index);                 // 에러
                    System.out.printf("%d. 영화: %s, 극장: %s, 시간: %s\n", s.getId(), m.getMovieName(), t.getThaterName(), s.getRunningTime());
                }
            }
        }
        list = temp; // 영화정보에 맞게 list 초기화

        if (login.getId() == 1) {
            String message = "1. 등록 2. 수정 3. 삭제 0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 3);
            if (userChoice == 1) {
                insert(m);
            } else if (userChoice == 2) {
                update(m);
            } else if (userChoice == 3) {
                delete(m);
            } else if (userChoice == 0) { // 뒤로가기
                movieViewer.movieOne(m.getId());
            }
        } else {
            String message = "0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 0);
            if (userChoice == 0) { // 뒤로가기
                movieViewer.movieOne(m.getId());
            }

        }
    }
    public void insert(TheaterDTO t) {
        System.out.printf("극장이름: %s\n", t.getThaterName());
        for(MovieDTO m : movieList){
            System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
        }
        String message = "해당 극장의 상영영화를 선택해주세요.";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while(!movieList.contains(new MovieDTO(userChoice))){
            message = "해당 영화는 존재하지않습니다.";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        message = "상영시간을 입력해주세요.";
        String runningTime = ScannerUtill.nextLine(SCANNER, message);

        ScreenInfoDTO s = new ScreenInfoDTO();
        s.setMovieId(userChoice);
        s.setTheaterId(t.getId());
        s.setRunningTime(runningTime);
        screenInfoController.insert(s);
        showTheaterInfo(t);

    }

    public void insert(MovieDTO m) {
        System.out.printf("영화이름: %s\n", m.getMovieName());
        for(TheaterDTO t : theaterList){
            System.out.printf("%d. %s\n", t.getId(), t.getThaterName());
        }
        String message = "해당 영화의 상영극장을 선택해주세요.";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while(!theaterList.contains(new TheaterDTO(userChoice))){
            message = "해당 상영극장이 존재하지않습니다.";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        message = "상영시간을 입력해주세요.";
        String runningTime = ScannerUtill.nextLine(SCANNER, message);

        ScreenInfoDTO s = new ScreenInfoDTO();
        s.setMovieId(m.getId());
        s.setTheaterId(userChoice);
        s.setRunningTime(runningTime);
        screenInfoController.insert(s);
        showMovieInfo(m);

    }


    public void update(TheaterDTO t) {
        String message = "수정할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new ScreenInfoDTO(userChoice))) {
            if(userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "수정할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showTheaterInfo(t);
        }else{
            ScreenInfoDTO s = new ScreenInfoDTO(userChoice);
            for(MovieDTO m : movieList){
                System.out.printf("%d. %s\n", m.getId(), m.getMovieName());
            }
            message = "해당 상영정보의 상영영화를 선택해주세요.";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
            while(!movieList.contains(new MovieDTO(userChoice))){
                message = "해당 영화는 존재하지않습니다.";
                userChoice = ScannerUtill.nextInt(SCANNER, message);
            }
            s.setMovieId(userChoice);
//            for(TheaterDTO theaterDTO : theaterList){
//                System.out.printf("%d. %s\n", theaterDTO.getId(), theaterDTO.getThaterName());
//            }
//            message = "해당 상영정보의 상영극장을 선택해주세요.";
//            userChoice = ScannerUtill.nextInt(SCANNER, message);
//            while(!theaterList.contains(new TheaterDTO(userChoice))){
//                message = "해당 상영극장이 존재하지않습니다.";
//                userChoice = ScannerUtill.nextInt(SCANNER, message);
//            }
//            s.setTheaterId(userChoice);
            s.setTheaterId(t.getId());

            message = "상영시간을 입력해주세요.";
            String runningTime = ScannerUtill.nextLine(SCANNER, message);
            s.setRunningTime(runningTime);

            screenInfoController.update(s);

            showTheaterInfo(t);
        }
    }
    public void update(MovieDTO m) {
        String message = "수정할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new ScreenInfoDTO(userChoice))) {
            if(userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "수정할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showMovieInfo(m);
        }else{
            ScreenInfoDTO s = new ScreenInfoDTO(userChoice);
            for(TheaterDTO t : theaterList){
                System.out.printf("%d. %s\n", t.getId(), t.getThaterName());
            }
            message = "해당 상영정보의 극장을 선택해주세요.";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
            while(!theaterList.contains(new TheaterDTO(userChoice))){
                message = "해당 극장은 존재하지않습니다.";
                userChoice = ScannerUtill.nextInt(SCANNER, message);
            }
            s.setTheaterId(userChoice);
//            for(MovieDTO movieDTO : movieList){
//                System.out.printf("%d. %s\n", movieDTO.getId(), movieDTO.getMovieName());
//            }
//            message = "해당 상영정보의 영화를 선택해주세요.";
//            userChoice = ScannerUtill.nextInt(SCANNER, message);
//            while(!movieList.contains(new MovieDTO(userChoice))){
//                message = "해당 영화는 존재하지않습니다.";
//                userChoice = ScannerUtill.nextInt(SCANNER, message);
//            }
//            s.setMovieId(userChoice);
            s.setMovieId(m.getId());
            message = "상영시간을 입력해주세요.";
            String runningTime = ScannerUtill.nextLine(SCANNER, message);
            s.setRunningTime(runningTime);

            screenInfoController.update(s);

            showMovieInfo(m);
        }
    }


    public void delete(TheaterDTO t) {
        String message = "삭제할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new ScreenInfoDTO(userChoice))) {
            if(userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "삭제할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showTheaterInfo(t);
        }else{
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtill.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("y")) {
                screenInfoController.delete(userChoice);
                showTheaterInfo(t);

            }
            else{
                showTheaterInfo(t);
            }
        }
    }
    public void delete(MovieDTO m) {
        String message = "삭제할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while (!list.contains(new ScreenInfoDTO(userChoice))) {
            if(userChoice == 0) break;
            System.out.println("잘못 입력하셨습니다.");
            message = "삭제할 상영번호를 입력하거나 뒤로가실려면 \'0\'를 입력해주세요 ";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if (userChoice == 0) {
            showMovieInfo(m);
        }else{
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtill.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("y")) {
                screenInfoController.delete(userChoice);
                showMovieInfo(m);

            }
            else{
                showMovieInfo(m);
            }
        }
    }
    public void deleteAllById(MovieDTO m){
        ArrayList<ScreenInfoDTO> list = new ArrayList<>();
        for(ScreenInfoDTO s : list){
            if(s.getId() == m.getId()){
                screenInfoController.delete(m.getId());
            }
        }
    }
    public void deleteAllById(TheaterDTO t){
        ArrayList<ScreenInfoDTO> list = new ArrayList<>();
        for(ScreenInfoDTO s : list){
            if(s.getId() == t.getId()){
                screenInfoController.delete(t.getId());
            }
        }
    }


}