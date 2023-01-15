package viewer;

import controller.ReviewController;

import java.util.Scanner;

public class ReviewViewer {
    private Scanner SCANNER;
    private ReviewController reviewController;
    public ReviewViewer(Scanner scanner){
        SCANNER = scanner;
        reviewController = new ReviewController();
    }
    public void showRank(){ // 평점보기
        // print 전체평점, 평론가평점, 일반평점
        // 1. 평점등록 2. 평점수정 3. 평점삭제 4. 뒤로가기
    }
    public void showReview(){   //평론보기
        // print 평론
        // 1. 평론등록 2. 평론수정 3. 평론삭제 4. 뒤로가기
    }
    public void rankInsert(){   // 평점추가
        
    }
    public void rankUpdage(){   // 평점수정
        
    }
    public void rankDelete(){   // 평점삭제
        
    }
    public void reviewInsert(){ // 평론추가
        
    }
    public void reviewUpdage(){ // 평론수정
        
    }
    public void reviewDelete(){ // 평론삭제
        
    }
}
