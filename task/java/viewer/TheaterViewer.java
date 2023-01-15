package viewer;

import controller.ReviewController;
import controller.TheaterController;

import java.util.Scanner;

public class TheaterViewer {
    private Scanner SCANNER;
    private TheaterController theaterController;
    public TheaterViewer(Scanner scanner){
        SCANNER = scanner;
        theaterController = new TheaterController();
    }

    public void showTheater(){  // 극장선택
        // print 극장번호, 극장이름
        // 일반사용자
        //      print 극장번호를 입력 또는 뒤로가기('0')
        
    }
    public void theaterOne(int id){ // 극장상세보기
        // print 극장이름, 극장위치, 극장번호
        // 일반사용자
        //      1. 상영중인영화정보 2. 뒤로가기
        // 관리자
        //      1. 상영중인영화정보 2. 극장추가 3. 극장수정 4. 극장삭제 5. 뒤로가기
    }
    
}
