package controller;

import model.BoardDTO;
import model.CommentDTO;

import java.util.ArrayList;

public class CommentController {
    private ArrayList<CommentDTO> list;
    private int nextId;

    public CommentController(){
        list = new ArrayList<>();
        nextId = 1;
    }
    public void add(CommentDTO commentDTO){
        commentDTO.setId(nextId++);
        list.add(commentDTO);
    }
    public CommentDTO selectById(int id){
        CommentDTO c = new CommentDTO(id);
        if(list.contains(c)){
            return new CommentDTO(list.get(list.indexOf(c)));
        }
        return null;
    }
    public ArrayList<CommentDTO> selectAllBoardId(int BoardId){
        ArrayList<CommentDTO> temp = new ArrayList<>();
        for (CommentDTO c : list){
            if(c.getBoardId() == BoardId){
                temp.add(new CommentDTO(c));
            }
        }
        return temp;
    }
    public ArrayList<CommentDTO> selectAll(){
        ArrayList<CommentDTO> temp = new ArrayList<>();
        for (CommentDTO c : list){
            temp.add(new CommentDTO(c));
        }
        return temp;
    }
    public void update(CommentDTO c){
        int index = list.indexOf(c);
        list.set(index, c);
    }
    public void delete(int id){
        list.remove(new CommentDTO(id));
    }

    public ArrayList<CommentDTO> selectAllWriteId(int id){    // 로그인 업데이트 변경
        ArrayList<CommentDTO> temp = new ArrayList<>();
        for (CommentDTO c : list){
            if(c.getWriterId() == id){
                temp.add(new CommentDTO(c));
            }
        }
        return temp;
    }

}
