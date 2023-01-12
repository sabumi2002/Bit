package model;

public class BoardDTO {
    private int id;
    private String title;
    private int writerId;
    private String writerNickname;
    private String content;

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterNickname() {
        return writerNickname;
    }

    public void setWriterNickname(String writerNickname) {
        this.writerNickname = writerNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (o instanceof BoardDTO) {
            BoardDTO b = (BoardDTO) o;
            return id == b.id;
        }
        return false;
    }

    public BoardDTO(BoardDTO origin) {
        id = origin.id;
        title = origin.title;
        writerId = origin.writerId;
        writerNickname = origin.writerNickname;
        content = origin.content;
    }

    public BoardDTO() {

    }

    public BoardDTO(int id) {
        this.id = id;
    }















    /*
    private int id;
    private String title;
    private String writer;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object o){
        if(o instanceof BoardDTO){
            BoardDTO b = (BoardDTO) o;
            return id == b.id;
        }
        return false;
    }
    public BoardDTO(BoardDTO origin){
        id = origin.id;
        title = origin.title;
        writer = origin.writer;
        content= origin.content;
    }
    public BoardDTO(){

    }
    */
}
