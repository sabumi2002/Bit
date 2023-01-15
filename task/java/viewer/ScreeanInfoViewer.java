package viewer;

import controller.ScreenInfoController;
import model.MovieDTO;
import model.TheaterDTO;

import java.util.Scanner;

public class ScreeanInfoViewer {
    private Scanner SCANNER;
    private ScreenInfoController screenInfoController;
    public ScreeanInfoViewer(Scanner scanner){
        SCANNER = scanner;
        screenInfoController = new ScreenInfoController();
    }
    public void showScreeanInfo(MovieDTO m, TheaterDTO t){
        // 영화이름, 극장이름, 상영시간
        // 일반사용자
        //      0. 뒤로가기
        // 관리자
        //      1. 등록 2. 수정 3. 삭제 0. 뒤로가기
    }
}
