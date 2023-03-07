package controller;

import com.mysql.cj.result.StringValueFactory;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import model.MovieDTO;
import model.ScheduleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleController {
    private Connection connection;

    public ScheduleController(ConnectionMaker connectionMaker) {
        this.connection = connectionMaker.makeConnection();
    }

    public void insert(ScheduleDTO s) {
        String query = "INSERT INTO `movie`.`schedule`(`movie_id`, `cinema_id`, `start_time`, `end_time`, `screening_date`, `room`) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, s.getMovieId());
            pstmt.setInt(2, s.getCinemaId());
            pstmt.setString(3, s.getStartTime());
            pstmt.setString(4, s.getEndTime());
            pstmt.setString(5, sdf.format(s.getScreeningDate()));
            pstmt.setInt(6, s.getRoom());



            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ScheduleDTO> selectAll(int cinema) {
        ArrayList<ScheduleDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `schedule` WHERE `cinema_id`= ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, cinema);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                ScheduleDTO s = new ScheduleDTO();
                s.setId(resultSet.getInt("id"));
                s.setMovieId(resultSet.getInt("movie_id"));
                s.setCinemaId(resultSet.getInt("cinema_id"));
                s.setStartTime(resultSet.getString("start_time"));
                s.setEndTime(resultSet.getString("end_time"));
                s.setScreeningDate(resultSet.getTimestamp("screening_date"));
                s.setRoom(resultSet.getInt("room"));

                list.add(s);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ScheduleDTO selectOne(int id) {
        ScheduleDTO s = null;
        String query = "SELECT * FROM `movie`.`schedule` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                s = new ScheduleDTO();
                s.setId(resultSet.getInt("id"));
                s.setMovieId(resultSet.getInt("movie_id"));
                s.setCinemaId(resultSet.getInt("cinema_id"));
                s.setStartTime(resultSet.getString("start_time"));
                s.setEndTime(resultSet.getString("end_time"));
                s.setScreeningDate(resultSet.getTimestamp("screening_date"));
                s.setRoom(resultSet.getInt("room"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    public void update(ScheduleDTO s) {
        String query = "UPDATE `movie`.`schedule` SET `start_time` = ?, `end_time` = ?, `room`=?, `screening_date`=? WHERE `id` = ?";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, s.getStartTime());
            pstmt.setString(2, s.getEndTime());
            pstmt.setInt(3, s.getRoom());
            pstmt.setString(4, sdf.format(s.getScreeningDate()));
            pstmt.setInt(5, s.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM `movie`.`schedule` WHERE `id` = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 극장이 갖고있는 영화번호 리스트
    public ArrayList<Integer> cinemaMovieList(int cinema) {
        String query = "SELECT DISTINCT movie_id FROM movie.schedule\n" +
                "WHERE cinema_id = ?";
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, cinema);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int movieid = resultSet.getInt("movie_id");
                list.add(movieid);
            }

            resultSet.close();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ScheduleDTO> selectAll(int cinemaId, int movieId) {
        ArrayList<ScheduleDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `schedule` WHERE `cinema_id`= ? AND `movie_id`= ?\n" +
                "ORDER BY `start_time`";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, cinemaId);
            pstmt.setInt(2, movieId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                ScheduleDTO s = new ScheduleDTO();
                s.setId(resultSet.getInt("id"));
                s.setMovieId(resultSet.getInt("movie_id"));
                s.setCinemaId(resultSet.getInt("cinema_id"));
                s.setStartTime(resultSet.getString("start_time"));
                s.setEndTime(resultSet.getString("end_time"));
                s.setRoom(resultSet.getInt("room"));
                s.setScreeningDate(resultSet.getTimestamp("screening_date"));

                list.add(s);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<ScheduleDTO> selectAllCinemaRoom(int cinemaId, int room) {
        ArrayList<ScheduleDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `schedule` WHERE `cinema_id`= ? AND `room`= ?\n" +
                "ORDER BY `start_time`";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, cinemaId);
            pstmt.setInt(2, room);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                ScheduleDTO s = new ScheduleDTO();
                s.setId(resultSet.getInt("id"));
                s.setMovieId(resultSet.getInt("movie_id"));
                s.setCinemaId(resultSet.getInt("cinema_id"));
                s.setStartTime(resultSet.getString("start_time"));
                s.setEndTime(resultSet.getString("end_time"));
                s.setRoom(resultSet.getInt("room"));
                s.setScreeningDate(resultSet.getTimestamp("screening_date"));

                list.add(s);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public String minuteToHour(String time, int minute) {
//        HashMap<String, String> result = new HashMap<>();
        String result = "";

        int h = minute / 60;
        int m = minute % 60;

        int baseTime = Integer.parseInt(time.replace(":", ""));

        int baseHour = baseTime / 100;
        int baseMinute = baseTime % 100;


        baseHour += h;
        baseMinute += m;

        if (baseMinute >= 60) {
            baseHour++;
            baseMinute = baseMinute % 60;
        }
        if (baseHour >= 24) {
            baseHour = baseHour % 24;
        }


        String tempHour;
        String tempMinute;

        if (baseHour < 10) {
            tempHour = "0" + baseHour;
        } else {
            tempHour = Integer.toString(baseHour);
        }
        if (baseMinute < 10) {
            tempMinute = "0" + baseMinute;
        } else {
            tempMinute = Integer.toString(baseMinute);
        }


        result = tempHour + ":" + tempMinute;


        return result;
    }


    public int HourToMinute(String time) {
        int result = 0;

        time = time.replace(":", "");

        int tempTime = Integer.parseInt(time);


        int h = tempTime / 100;
        int m = tempTime % 100;


        result = (h * 60) + m;


        return result;
    }
}
