package model;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDTO {
    int id;
    int movieId;
    int cinemaId;
    String runningTime;
    Date screeningDate;
    int room;
}
