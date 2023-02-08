package controller;

import model.MovieDTO;
import model.ReviewDTO;

import java.util.ArrayList;

public class ReviewController {
    private ArrayList<ReviewDTO> list;
    private int nextId;

    public ReviewController() {
        list = new ArrayList<>();
        nextId = 1;

        for (int i = 1; i < 4; i++) {  // 1번영화 리뷰들
            ReviewDTO r = new ReviewDTO();
            r.setReview("1번영화 리뷰내용............" + i);
            r.setGrade(i);  //별점
            r.setMovieId(1);
            r.setUserId(i);
            insert(r);
        }
        for (int i = 1; i < 4; i++) {  // 2번영화 리뷰들
            ReviewDTO r = new ReviewDTO();
            r.setReview("2번영화 리뷰내용............" + i);
            r.setGrade(i + 1);
            r.setMovieId(2);
            r.setUserId(i);
            insert(r);
        }
    }

    public void insert(ReviewDTO r) {
        r.setId(nextId++);
        list.add(r);
    }

    public void update(ReviewDTO r) {
        int index = list.indexOf(r);
        list.set(index, r);
    }

    public void delete(int id) {
        ReviewDTO r = new ReviewDTO(id);
        list.remove(r);
    }

    public ReviewDTO selectById(int id) {
        for (ReviewDTO r : list) {
            if (r.getId() == id) {
                return new ReviewDTO(r);
            }
        }
        return null;
    }

    public ArrayList<ReviewDTO> selcetAll(int movieID) {
        ArrayList<ReviewDTO> temp = new ArrayList<>();
        for (ReviewDTO r : list) {
            if (r.getMovieId() == movieID) {
                temp.add(new ReviewDTO(r));
            }
        }
        return temp;
    }

    public ArrayList<ReviewDTO> selcetAll() {
        ArrayList<ReviewDTO> temp = new ArrayList<>();
        for (ReviewDTO r : list) {
            temp.add(new ReviewDTO(r));
        }
        return temp;
    }
}
