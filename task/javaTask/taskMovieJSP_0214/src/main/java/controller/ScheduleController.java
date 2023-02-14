package controller;

import connector.ConnectionMaker;
import model.ScheduleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleController {
    private Connection connection;

    public ScheduleController(ConnectionMaker connectionMaker) {
        this.connection = connectionMaker.makeConnection();
    }

    public void insert(ScheduleDTO s) {
        String query = "INSERT INTO `movie`.`schedule`(`movie_id`, `cinema_id`, `running_time`) " +
                "VALUES(?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, s.getMovieId());
            pstmt.setInt(2, s.getCinemaId());
            pstmt.setString(3, s.getRunningTime());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ScheduleDTO> selectAll() {
        ArrayList<ScheduleDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `schedule` ORDER BY `id` DESC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                ScheduleDTO s = new ScheduleDTO();
                s.setId(resultSet.getInt("id"));
                s.setMovieId(resultSet.getInt("movie_id"));
                s.setCinemaId(resultSet.getInt("cinema_id"));
                s.setRunningTime(resultSet.getString("running_time"));

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
                s.setRunningTime(resultSet.getString("running_time"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    public void update(ScheduleDTO s) {
        String query = "UPDATE `movie`.`schedule` SET `running_time` = ? WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, s.getRunningTime());
            pstmt.setInt(2, s.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
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
}
