package controller;

import model.MovieDTO;
import model.ReviewDTO;

import java.util.ArrayList;

public class MovieController {
    private ArrayList<MovieDTO> list;
    private int nextId;

    public MovieController(){
        list = new ArrayList<>();
        nextId = 1;

        for(int i = 1; i< 4;i++) {
            MovieDTO m = new MovieDTO();
            m.setMovieName("영화"+i);
            m.setContent("영화"+i+ " 줄거리");
            m.setRank(1);
            insert(m);
        }
        MovieDTO m = new MovieDTO();
        m.setMovieName("영화4");
        m.setContent("영화4 줄거리");
        m.setRank(2);
        insert(m);

    }
    public void insert(MovieDTO m) {
        m.setId(nextId++);
        list.add(m);
    }

    public void update(MovieDTO m) {
        int index= list.indexOf(m);
        list.set(index, m);
    }

    public void delete(int id) {
        MovieDTO m = new MovieDTO(id);
        list.remove(m);
    }

    public MovieDTO selectById(int id) {
        for(MovieDTO m : list){
            if(m.getId() ==  id){
                return new MovieDTO(m);
            }
        }
        return null;
    }

    public ArrayList<MovieDTO> selcetAll() {
        ArrayList<MovieDTO> temp = new ArrayList<>();
        for(MovieDTO m : list){
            temp.add(new MovieDTO(m));
        }
        return temp;
    }
}























