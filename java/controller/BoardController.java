package controller;

import model.BoardDTO;

import java.util.ArrayList;

public class BoardController {
    private ArrayList<BoardDTO> list;
    private int nextId;

    public BoardController() {
        list = new ArrayList<>();
        nextId = 1;

        for (int i = 1; i <= 4; i++) {
            BoardDTO b = new BoardDTO();
            b.setTitle("제목 " + i);
            b.setWriterId(1);
            b.setWriterNickname("일반회원1");
            b.setContent(i + "번째 게시글의 내용입니다.");

            add(b);
        }
    }

    public void add(BoardDTO boardDTO) {
        boardDTO.setId(nextId++);
        list.add(boardDTO);
    }

    public BoardDTO selectOne(int id) {
        BoardDTO temp = new BoardDTO(id);
        if (list.contains(temp)) {
            return new BoardDTO(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<BoardDTO> selectAll() {
        ArrayList<BoardDTO> temp = new ArrayList<>();
        for (BoardDTO b : list) {
            temp.add(new BoardDTO(b));
        }

        return temp;
    }

    public void update(BoardDTO boardDTO) {
        list.set(list.indexOf(boardDTO), boardDTO);
    }

    public void delete(int id) {
        list.remove(new BoardDTO(id));
    }


















    /*
    private ArrayList<BoardDTO> list;
    private int nextId;

    public BoardContoller(){
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insertBoard(BoardDTO boardDTO){
        boardDTO.setId(nextId++);
        list.add(boardDTO);
    }
    public BoardDTO selectById(int id){
        for(BoardDTO b : list){
            if(b.getId() == id){
                return new BoardDTO(b);
            }
        }
        return null;
    }
    public void update(BoardDTO boardDTO){
        list.set(list.indexOf(boardDTO), boardDTO);
    }
    public void delete(int id){
        BoardDTO b = new BoardDTO();
        b.setId(id);
        list.remove(b);
    }
    public boolean empty(){
        if (list.size()==0){
            return true;
        }
        return false;
    }
    public void printList(){
        for(BoardDTO b:list){
            System.out.printf("%d. %s\n", b.getId(), b.getTitle());
        }
    }
    */
}
