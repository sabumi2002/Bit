import viewer.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Movie {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        UserViewer userViewer = new UserViewer(scanner);
        MovieViewer movieViewer = new MovieViewer(scanner);
        TheaterViewer theaterViewer = new TheaterViewer(scanner);
        ScreeanInfoViewer screeanInfoViewer = new ScreeanInfoViewer(scanner);
        ReviewViewer reviewViewer = new ReviewViewer(scanner);

        userViewer.setMovieViewer(movieViewer); // 유저뷰<영화뷰 의존성주입
        userViewer.setTheaterViewer(theaterViewer);
        userViewer.setScreeanInfoViewer(screeanInfoViewer);
        userViewer.setReviewViewer(reviewViewer);
        theaterViewer.setScreeanInfoViewer(screeanInfoViewer);
        theaterViewer.setMovieViewer(movieViewer);
        theaterViewer.setUserViewer(userViewer);
        movieViewer.setUserViewer(userViewer);
        movieViewer.setScreeanInfoViewer(screeanInfoViewer);
        movieViewer.setReviewViewer(reviewViewer);
        movieViewer.setTheaterViewer(theaterViewer);
        screeanInfoViewer.setTheaterViewer(theaterViewer);
        screeanInfoViewer.setMovieViewer(movieViewer);
        reviewViewer.setUserViewer(userViewer);
        reviewViewer.setMovieViewer(movieViewer);


        userViewer.showLoginPage();
    }
}
