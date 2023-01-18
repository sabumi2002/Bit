package viewer;

import controller.FilmController;
import model.MemberDTO;
import util.ScannerUtil;

import java.util.Scanner;

public class FilmViewer {
    private final int LEVEL_GENERAL = 1;
    private final int LEVEL_CRITIC = 2;
    private final int LEVEL_ADMIN = 3;
    private final Scanner SCANNER;
    private FilmController filmController;
    private MemberDTO logIn;
    public FilmViewer(Scanner scanner){
        SCANNER = scanner;
    }

    public void setLogIn(MemberDTO logIn) {
        this.logIn = logIn;
    }
    public void showMenu(){
        if(logIn.getLevel() == LEVEL_ADMIN){
            showAdminMenu();
        }else{
            showGeneralMenu();
        }
    }
    private void showAdminMenu(){
        String message = "1. 영화 목록 보기 2. 영화 등록 하기 3. 뒤로 가기";
        while(true){
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if(userChoice == 1){

            }else if(userChoice == 2){

            }else if (userChoice == 3){
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }
        }
    }
    private void showGeneralMenu(){
        String message = "1. 영화 목록 보기 2. 뒤로 가기";
        while(true){
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if(userChoice == 1){

            }else if(userChoice == 2) {
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }
        }
    }
}













