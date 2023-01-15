package controller;

import model.MovieDTO;
import model.ReviewDTO;
import model.TheaterDTO;

import java.util.ArrayList;

public class TheaterController {
    private ArrayList<TheaterDTO> list;
    private int nextId;

    public TheaterController(){
        list = new ArrayList<>();
        nextId = 1;

        TheaterDTO m = new TheaterDTO();
        m.setLocation("ㄱ구 ㄱ동 12-2번지");
        m.setPhoneNumber("010-1234-1234");
        m.setThaterName("ㄱ극장");
        insert(m);

        m.setLocation("ㄴ구 ㄴ동 1-3번지");
        m.setPhoneNumber("010-0000-0001");
        m.setThaterName("ㄴ극장");
        insert(m);

        m.setLocation("ㄷ구 ㄷ동 33-2번지");
        m.setPhoneNumber("010-5555-5432");
        m.setThaterName("ㄷ극장");
        insert(m);
    }
    public void insert(TheaterDTO t) {
        t.setId(nextId++);
        list.add(t);
    }

    public void update(TheaterDTO t) {
        int index= list.indexOf(t);
        list.set(index, t);
    }

    public void delete(int id) {
        TheaterDTO t = new TheaterDTO(id);
        list.remove(t);
    }

    public TheaterDTO selectById(int id) {
        for(TheaterDTO t : list){
            if(t.getId() ==  id){
                return new TheaterDTO(t);
            }
        }
        return null;
    }

    public ArrayList<TheaterDTO> selcetAll() {
        ArrayList<TheaterDTO> temp = new ArrayList<>();
        for(TheaterDTO t : list){
            temp.add(new TheaterDTO(t));
        }
        return temp;
    }
}
