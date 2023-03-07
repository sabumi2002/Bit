package model;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDTO {
    int id;
    int movieId;
    int cinemaId;
    String startTime;
    String endTime;
    Date screeningDate;
    int room;
}
