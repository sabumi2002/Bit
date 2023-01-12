package controller;

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

}
