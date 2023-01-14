package controller;

import model.UserDTO;

import java.util.ArrayList;

public class UserController {
    private ArrayList<UserDTO> list;
    private int nextId;

    public UserController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(UserDTO u) {
        u.setId(nextId++);
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
}
