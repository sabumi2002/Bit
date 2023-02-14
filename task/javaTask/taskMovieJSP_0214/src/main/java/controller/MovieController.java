package controller;

import connector.ConnectionMaker;
import model.MovieDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieController {
    private Connection connection;

    public MovieController(ConnectionMaker connectionMaker) {
        this.connection = connectionMaker.makeConnection();
    }

    public void insert(MovieDTO movieDTO) {
        String query = "INSERT INTO `board`(`title`, `content`, `rank`) " +
                "VALUES(?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, movieDTO.getTitle());
            pstmt.setString(2, movieDTO.getContent());
            pstmt.setInt(3, movieDTO.getRank());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MovieDTO> selectAll() {
        ArrayList<MovieDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `movie`.`movie` ORDER BY `id` DESC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                MovieDTO m = new MovieDTO();
                m.setId(resultSet.getInt("id"));
                m.setTitle(resultSet.getString("title"));
                m.setContent(resultSet.getString("content"));
                m.setRank(resultSet.getInt("rank"));

                list.add(m);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public MovieDTO selectOne(int id) {
        MovieDTO m = null;
        String query = "SELECT * FROM `movie`.`movie` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                m = new MovieDTO();
                m.setId(resultSet.getInt("id"));
                m.setTitle(resultSet.getString("title"));
                m.setContent(resultSet.getString("content"));
                m.setRank(resultSet.getInt("rank"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }

    public void update(MovieDTO m) {
        String query = "UPDATE `board` SET `title` = ?, `content` = ?, `rank` = ? WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, m.getTitle());
            pstmt.setString(2, m.getContent());
            pstmt.setInt(3, m.getRank());
            pstmt.setInt(4, m.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String query = "DELETE FROM `movie`.`movie` WHERE `id` = ?";

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
