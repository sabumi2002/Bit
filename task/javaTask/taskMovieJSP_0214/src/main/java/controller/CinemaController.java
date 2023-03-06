package controller;

import connector.ConnectionMaker;
import model.CinemaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CinemaController {
    private Connection connection;

    public CinemaController(ConnectionMaker connectionMaker) {
        this.connection = connectionMaker.makeConnection();
    }

    public void insert(CinemaDTO cinemaDTO) {

        String query = "INSERT INTO `movie`.`cinema`(`name`, `location`, `phone_number`, `img`, `room`) " +
                "VALUES(?, ?, ?, ?, ?)";
        if (cinemaDTO.getImg() == null) {
            query = "INSERT INTO `movie`.`cinema`(`name`, `location`, `phone_number`) VALUES(?, ?, ?)";
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, cinemaDTO.getName());
            pstmt.setString(2, cinemaDTO.getLocation());
            pstmt.setString(3, cinemaDTO.getPhoneNumber());
            if (cinemaDTO.getImg() != null) {
                pstmt.setString(4, cinemaDTO.getImg());
            }

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CinemaDTO> selectAll() {
        ArrayList<CinemaDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `movie`.`cinema` ORDER BY `name`";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                CinemaDTO c = new CinemaDTO();
                c.setId(resultSet.getInt("id"));
                c.setName(resultSet.getString("name"));
                c.setLocation(resultSet.getString("location"));
                c.setPhoneNumber(resultSet.getString("phone_number"));
                c.setImg(resultSet.getString("img"));
                c.setRoom(resultSet.getInt("room"));

                list.add(c);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public CinemaDTO selectOne(int id) {
        CinemaDTO c = null;
        String query = "SELECT * FROM `cinema` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                c = new CinemaDTO();
                c.setId(resultSet.getInt("id"));
                c.setName(resultSet.getString("name"));
                c.setLocation(resultSet.getString("location"));
                c.setPhoneNumber(resultSet.getString("phone_number"));
                c.setImg(resultSet.getString("img"));
                c.setRoom(resultSet.getInt("room"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public void update(CinemaDTO c) {
        String query = "UPDATE `movie`.`cinema` SET `name` = ?, `location` = ?, `phone_number` =?, `img` = ?, `room`=? WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getLocation());
            pstmt.setString(3, c.getPhoneNumber());
            pstmt.setString(4, c.getImg());
            pstmt.setInt(5, c.getRoom());

            pstmt.setInt(6, c.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM `movie`.`cinema` WHERE `id` = ?";

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
