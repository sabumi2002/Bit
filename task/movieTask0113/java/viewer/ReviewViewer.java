package viewer;

import controller.ReviewController;
import model.MovieDTO;
import model.ReviewDTO;
import model.UserDTO;
import utill.ScannerUtill;

import java.util.ArrayList;
import java.util.Scanner;

public class ReviewViewer {
    private Scanner SCANNER;
    private ReviewController reviewController;
    private UserDTO login;
    private ArrayList<UserDTO> userList;
    private UserViewer userViewer;
    private MovieViewer movieViewer;

    public void setLogin(UserDTO login) {
        this.login = login;
    }

    public void setUserList(ArrayList<UserDTO> userList) {
        this.userList = userList;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public ReviewViewer(Scanner scanner) {
        SCANNER = scanner;
        reviewController = new ReviewController();
    }

    public void showRank(MovieDTO m) { // 평점보기
        // print 전체평점, 평론가평점, 일반평점
        // 1. 평점등록 2. 평점수정 3. 평점삭제 0. 뒤로가기
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());  // 영화에 맞게 list 초기화
        userList();     // userList 가져옴
        printRank(m.getId());    // 평점 출력

        if(login.getRank() == 1){
            System.out.println("<관리자입니다>");
            String message = "1. 평점삭제하기 0.뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 1);
            if(userChoice == 1){
                deleteManager(m);
            }else if(userChoice==0){
                movieViewer.movieOne(m.getId());
            }
        }else {
            String message = "1. 평점등록 2. 평점수정 3. 평점삭제 0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 3);
            if (userChoice == 1) {
                rankInsert(m);
            } else if (userChoice == 2) {
                rankUpdate(m);
            } else if (userChoice == 3) {
                rankDelete(m);
            } else if (userChoice == 0) {
                movieViewer.movieOne(m.getId());
            }
        }
    }

    public void printRank(int movieId) {    // 평점출력
        int rankNomal = 0;
        int rankReviewer = 0;
        int nomalCount = 0;
        int reviewerCount = 0;

        ArrayList<ReviewDTO> list = reviewController.selcetAll(movieId);

        for (ReviewDTO r : list) {
            for (UserDTO u : userList) {
                if (r.getUserId() == u.getId() && u.getRank()!=1) {
                    System.out.printf("%d. <%s> 회원등급: %d 평점: %d\n", r.getId(), u.getNickname(), u.getRank(), r.getGrade());
                    if (u.getRank() == 2) {
                        rankReviewer += r.getGrade();
                        reviewerCount++;
                    } else if (u.getRank() == 3) {
                        rankNomal += r.getGrade();
                        nomalCount++;
                    }
                }
            }
        }

        double allAvg = rankAvg(rankNomal + rankReviewer, nomalCount + reviewerCount);
        double nomalAvg = rankAvg(rankNomal, nomalCount);
        double reviewerAvg = rankAvg(rankReviewer, reviewerCount);
        // print 전체평점, 평론가평점, 일반평점
        System.out.printf("전체평점: %.1f 평론가평점: %.1f 일반평점: %.1f\n", allAvg, reviewerAvg, nomalAvg);

    }

    public double rankAvg(int rank, int count) {
        double result = (double) rank / count;
        return result;
    }

    public void showReview(MovieDTO m) {   //평론보기
        // print 평론
        // 1. 평론등록 2. 평론수정 3. 평론삭제 4. 뒤로가기

        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId()); // 영화에 맞게 list 초기화
        userList();
        for (ReviewDTO r : list) {
            for (UserDTO u : userList) {
                if (r.getUserId() == u.getId()) {
                    System.out.printf("%d. <%s> 등급:%d %s\n", u.getId(), u.getNickname(),u.getRank(), r.getReview());
                }
            }
        }

        if(login.getRank() ==2) {   // 평론가
            String message = "1. 평론등록 2. 평론수정 3. 평론삭제 0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 3);
            if (userChoice == 1) {
                reviewInsert(m);
            } else if (userChoice == 2) {
                reviewUpdage(m);
            } else if (userChoice == 3) {
                reviewDelete(m);
            } else if (userChoice == 0) {
                movieViewer.movieOne(m.getId());
            }
        }else{  // 일반
            String message = "0. 뒤로가기";
            int userChoice = ScannerUtill.nextInt(SCANNER, message, 0, 0);
            if(userChoice == 0) {
                movieViewer.movieOne(m.getId());
            }
        }
    }

    public void userList() {
        userList = userViewer.getUserList();;
    }


    public void rankInsert(MovieDTO m) {   // 평점추가
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());
        boolean complete = false;
        for (ReviewDTO r : list) {
            if (r.getUserId() == login.getId()) {
                complete = true;
            }
        }
        if (complete == true) {
            System.out.println("이미 작성한 평점이 존재합니다.");
            showRank(m);
        } else {
            String message = "평점을 입력해주세요 (0~5)";
            int grade = ScannerUtill.nextInt(SCANNER, message, 0, 5);

            ReviewDTO r = new ReviewDTO();
            r.setUserId(login.getId());
            System.out.println(m.getId());
            r.setMovieId(m.getId());
            r.setGrade(grade);
            reviewController.insert(r);
            showRank(m);
        }
    }

    public void rankUpdate(MovieDTO m) {   // 평점수정
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());
        boolean complete = false;
        ReviewDTO reviewDTO = new ReviewDTO();
        for (ReviewDTO r : list) {
            if (r.getUserId() == login.getId()) {
                complete = true;
                reviewDTO = new ReviewDTO(r);
            }
        }
        if (complete) {
            String message = "새 평점을 입력해주세요 (0~5)";
            int rank = ScannerUtill.nextInt(SCANNER, message, 0, 5);

            ReviewDTO temp = new ReviewDTO(reviewDTO);
            temp.setGrade(rank);
            reviewController.update(temp);
            showRank(m);
        } else {
            System.out.println("수정할 평점이 없습니다.");
            showRank(m);
        }
    }

    public void rankDelete(MovieDTO m) {   // 평점삭제
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());
        boolean complete = false;
        ReviewDTO reviewDTO = new ReviewDTO();
        for (ReviewDTO r : list) {
            if (r.getUserId() == login.getId()) {
                complete = true;
                reviewDTO = new ReviewDTO(r);
            }
        }
        if (complete) {
            String message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtill.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("y")) {
                reviewController.delete(reviewDTO.getId());
                showRank(m);
            } else {
                showRank(m);
            }
        } else {
            System.out.println("평점이 존재하지않습니다.");
            showRank(m);
        }
    }
    public void deleteManager(MovieDTO m){
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());
        for (ReviewDTO r : list) {
            for (UserDTO u : userList) {
                if (r.getUserId() == u.getId() && u.getRank()!=1) {
                    System.out.printf("%d. <%s> 회원등급: %d 평점: %d\n", r.getId(), u.getNickname(), u.getRank(), r.getGrade());
                }
            }
        }

        String message = "삭제하실 평점을 선택해주시거나 뒤로가기'0'을 입력해주세요.";
        int userChoice = ScannerUtill.nextInt(SCANNER, message);
        while(!list.contains(new ReviewDTO(userChoice))){
            if (userChoice ==0) break;
            message = "삭제하실 평점을 선택해주시거나 뒤로가기'0'을 입력해주세요.";
            userChoice = ScannerUtill.nextInt(SCANNER, message);
        }
        if(userChoice==0) {
            showRank(m);
        }else{
            message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtill.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("y")) {
                reviewController.delete(userChoice);
                showRank(m);
            } else {
                showRank(m);
            }
        }
    }

    public void reviewInsert(MovieDTO m) { // 평론추가
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());
        boolean complete = false;
        for (ReviewDTO r : list) {
            if (r.getUserId() == login.getId()) {
                complete = true;
            }
        }
        if (complete == true) {
            System.out.println("이미 작성한 평론이 존재합니다.");
            showReview(m);
        }else{
            String message = "평론을 입력하세요.";
            String review = ScannerUtill.nextLine(SCANNER, message);

            ReviewDTO r = new ReviewDTO();
            r.setUserId(login.getId());
            r.setMovieId(m.getId());
            r.setReview(review);
            reviewController.insert(r);
            showReview(m);
        }
    }

    public void reviewUpdage(MovieDTO m) { // 평론수정
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());
        boolean complete = false;
        ReviewDTO reviewDTO = new ReviewDTO();
        for (ReviewDTO r : list) {
            if (r.getUserId() == login.getId()) {
                complete = true;
                reviewDTO = new ReviewDTO(r);
            }
        }
        if (complete) {
            String message = "새 평론을 입력해주세요.";
            String review = ScannerUtill.nextLine(SCANNER, message);

            ReviewDTO temp = new ReviewDTO(reviewDTO);
            temp.setReview(review);
            reviewController.update(temp);
            showReview(m);
        } else {
            System.out.println("이미 작성한 평론이 존재합니다.");
            showRank(m);
        }

    }

    public void reviewDelete(MovieDTO m) { // 평론삭제
        ArrayList<ReviewDTO> list = reviewController.selcetAll(m.getId());
        boolean complete = false;
        ReviewDTO reviewDTO = new ReviewDTO();
        for (ReviewDTO r : list) {
            if (r.getUserId() == login.getId()) {
                complete = true;
                reviewDTO = new ReviewDTO(r);
            }
        }
        if (complete) {
            String message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtill.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("y")) {
                reviewController.delete(reviewDTO.getId());
                showReview(m);
            } else {
                showReview(m);
            }
        } else {
            System.out.println("평점이 존재하지않습니다.");
            showReview(m);
        }
    }
    public void realTimeDelete(int userId){
        ArrayList<ReviewDTO> list = reviewController.selcetAll();
        for(ReviewDTO r : list){
            if(r.getUserId() == userId){
                reviewController.delete(r.getId());
            }
        }
    }
    public void realTimeRank(UserDTO u){
        ArrayList<ReviewDTO> list = reviewController.selcetAll();
        for(ReviewDTO r : list){
            if(r.getUserId() == u.getId()){
                r.setGrade(u.getRank());
                reviewController.update(r);
            }
        }
    }
}
