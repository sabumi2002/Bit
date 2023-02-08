package controller;

import model.ReviewDTO;
import model.ScreenInfoDTO;

import java.util.ArrayList;

public class ScreenInfoController {
    private ArrayList<ScreenInfoDTO> list;
    private int nextId;

    public ScreenInfoController(){
        list = new ArrayList<>();
        nextId = 1;
        for(int i = 1 ; i<4; i++){
            ScreenInfoDTO s = new ScreenInfoDTO();
            s.setMovieId(i);
            s.setRunningTime("14:00 - 16:00");
            s.setTheaterId(i);
            insert(s);
        }
        ScreenInfoDTO s = new ScreenInfoDTO();
        s.setMovieId(1);
        s.setRunningTime("16:00 - 18:00");
        s.setTheaterId(3);
        insert(new ScreenInfoDTO(s));

        s.setMovieId(2);
        s.setRunningTime("18:00 - 20:00");
        s.setTheaterId(1);
        insert(new ScreenInfoDTO(s));


    }
    public void insert(ScreenInfoDTO s) {
        s.setId(nextId++);
        list.add(s);


    }

    public void update(ScreenInfoDTO s) {
        int index= list.indexOf(s);
        list.set(index, s);
    }

    public void delete(int id) {
        ScreenInfoDTO s = new ScreenInfoDTO(id);
        list.remove(s);
    }

    public ScreenInfoDTO selectById(int id) {
        for(ScreenInfoDTO s : list){
            if(s.getId() ==  id){
                return new ScreenInfoDTO(s);
            }
        }
        return null;
    }

    public ArrayList<ScreenInfoDTO> selcetAll() {
        ArrayList<ScreenInfoDTO> temp = new ArrayList<>();
        for(ScreenInfoDTO s : list){
            temp.add(new ScreenInfoDTO(s));
        }
        return temp;
    }
}
