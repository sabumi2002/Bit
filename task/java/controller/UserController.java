package controller;

import model.UserDTO;

import java.util.ArrayList;

public class UserController {
    private ArrayList<UserDTO> list;
    private int nextId;
    private ArrayList<UserDTO> rankUpList;
    public UserController() {
        list = new ArrayList<>();
        rankUpList = new ArrayList<>();
        nextId = 1;

        for(int i =1; i< 4; i++){
            UserDTO u = new UserDTO();
            u.setId(nextId++);
            u.setUsername(Integer.toString(i));
            u.setPassword(Integer.toString(i));
            u.setNickname(Integer.toString(i));
            u.setRank(i);
            list.add(u);
        }

    }

    public void insert(UserDTO u) {
        u.setId(nextId++);
        u.setRank(3);
        list.add(u);
    }

    public void update(UserDTO u) {
        int index = list.indexOf(u);
        list.set(index, u);
    }

    public void delete(int id) {
        UserDTO u = new UserDTO(id);
        list.remove(u);
    }

    public UserDTO selectById(int id) {
        for (UserDTO u : list) {
            if (u.getId() == id) {
                return new UserDTO(u);
            }
        }
        return null;
    }

    public ArrayList<UserDTO> selectAll() {
        ArrayList<UserDTO> temp = new ArrayList<>();

        for (UserDTO u : list) {
            temp.add(new UserDTO(u));
        }
        return temp;
    }
    public UserDTO auth(String username, String password){
        for (UserDTO u : list){
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)){
                return new UserDTO(u);
            }
        }
        return null;
    }
    public ArrayList<UserDTO> getRankUpList(){
        ArrayList<UserDTO> temp = new ArrayList<>();
        for (UserDTO u : rankUpList) {
            temp.add(new UserDTO(u));
        }
        return temp;
    }
    public UserDTO getRankUpById(int id){
        for(UserDTO u : rankUpList){
            if(u.getId() == id){
                return new UserDTO(u);
            }
        }
        return null;
    }
    public void insertRankUp(UserDTO login){
        rankUpList.add(new UserDTO(login));
    }
    public void deleteRankUp(int id){
        UserDTO u = new UserDTO(id);
        rankUpList.remove(u);
    }
}
