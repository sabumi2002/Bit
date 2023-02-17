package model;

import lombok.Data;

@Data
public class MovieDTO {
    int id;
    String title;
    String content;
    String rank;
    String release;
    String img;
}
