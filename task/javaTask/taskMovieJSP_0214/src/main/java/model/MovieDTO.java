package model;

import lombok.Data;

@Data
public class MovieDTO {
    int id;
    String title;
    String content;
    int rank;
}
