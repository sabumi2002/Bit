package model;

public class ReviewDTO {    // 평점 DTO
    private int id;
    private int userId;
    private int movieId;
    private int grade;  // 별점 (0-5)
    private String review;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    public ReviewDTO(){

    }
    public ReviewDTO(ReviewDTO r){
        this.id = r.id;
        this.userId = r.userId;
        this.movieId = r.movieId;
        this.grade = r.grade;
        this.review = r.review;
    }
    public ReviewDTO(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ReviewDTO){
            ReviewDTO r = (ReviewDTO) o;
            return id == r.id;
        }
        return false;
    }
}







