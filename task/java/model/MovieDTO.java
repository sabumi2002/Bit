package model;

public class MovieDTO { // 영화 DTO
    private int id;
    private String movieName;
    private String content;
    private int rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public MovieDTO(){

    }
    public MovieDTO(MovieDTO m){
        this.id = m.id;
        this.movieName = m.movieName;
        this.content = m.content;
        this.rank = m.rank;
    }
    public MovieDTO(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof MovieDTO){
            MovieDTO m = (MovieDTO)o;
            return id == m.id;
        }
        return false;
    }
}
