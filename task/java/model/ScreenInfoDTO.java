package model;

public class ScreenInfoDTO { // 상영정보DTO
    private int id;
    private int movieId;
    private int theaterId;
    private String runningTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }
    public ScreenInfoDTO(){}
    public ScreenInfoDTO(ScreenInfoDTO s){
        this.id = s.id;
        this.movieId = s.movieId;
        this.theaterId = s.theaterId;
        this.runningTime = s.runningTime;
    }
    public ScreenInfoDTO(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof ScreenInfoDTO){
            ScreenInfoDTO s = (ScreenInfoDTO) o;
            return id == s.id;
        }
        return false;
    }
}













