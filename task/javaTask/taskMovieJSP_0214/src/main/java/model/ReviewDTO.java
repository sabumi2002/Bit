package model;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    int id;
    int writerId;
    int movieId;
    int rating;
    String review;
    Date entryDate;
    Date modifyDate;
}
