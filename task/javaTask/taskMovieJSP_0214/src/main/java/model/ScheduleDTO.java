package model;

import lombok.Data;

@Data
public class ScheduleDTO {
    int id;
    int movieId;
    int cinemaId;
    String runningTime;
}
