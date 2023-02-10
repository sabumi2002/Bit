package model;

public class CommentDTO {
    private int id; // 댓글 번호
    private int writerId; // 작성자 id
    private int boardId; // 게시글 id
    private String writerNickname; // 작성자 닉네임
    private String content; // 내용

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
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

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public boolean equals(Object o){
        if(o instanceof CommentDTO){
            CommentDTO c = (CommentDTO) o;
            return id == c.id;
        }
        return false;
    }
    public CommentDTO(CommentDTO c){
        id = c.id; // 댓글번호
        writerId = c.writerId; // 작성자 id
        boardId = c.boardId; // 게시글 id
        writerNickname = c.writerNickname; // 작성자 닉네임
        content = c.content; // 내용
    }
    public CommentDTO(){

    }
    public CommentDTO(int id){
        this.id = id;
    }
}








