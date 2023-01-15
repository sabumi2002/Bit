package controller;

import model.MovieDTO;

import java.util.ArrayList;

public interface iController {
    public void insert();

    void insert(MovieDTO m);

    public void update();
    public void delete();
    public void selectById();
    public void selcetAll();
}
