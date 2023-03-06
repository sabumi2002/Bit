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
    private final int PAGE_SIZE = 8;

    public MovieController(ConnectionMaker connectionMaker) {
        this.connection = connectionMaker.makeConnection();
    }

    public void insert(MovieDTO movieDTO) {
        String query = "INSERT INTO `movie`(`title`, `content`, `rank`, `release`, `img`, `length`) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, movieDTO.getTitle());
            pstmt.setString(2, movieDTO.getContent());
            pstmt.setString(3, movieDTO.getRank());
            pstmt.setString(4, movieDTO.getRelease());
            pstmt.setString(5, movieDTO.getImg());
            pstmt.setString(6, movieDTO.getLength());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MovieDTO> selectAll(int pageNo) {
        ArrayList<MovieDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `movie`.`movie` ORDER BY `id` LIMIT ?, ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, (pageNo - 1) * PAGE_SIZE);
            pstmt.setInt(2, PAGE_SIZE);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                MovieDTO m = new MovieDTO();
                m.setId(resultSet.getInt("id"));
                m.setTitle(resultSet.getString("title"));
                m.setContent(resultSet.getString("content"));
                m.setRank(resultSet.getString("rank"));
                m.setRelease(resultSet.getString("release"));
                m.setImg(resultSet.getString("img"));
                m.setLength(resultSet.getString("length"));

                list.add(m);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public ArrayList<MovieDTO> selectAll(String rank) {
        ArrayList<MovieDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `movie`.`movie` WHERE `rank` = ? ORDER BY `id` ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, rank);


            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                MovieDTO m = new MovieDTO();
                m.setId(resultSet.getInt("id"));
                m.setTitle(resultSet.getString("title"));
                m.setContent(resultSet.getString("content"));
                m.setRank(resultSet.getString("rank"));
                m.setRelease(resultSet.getString("release"));
                m.setImg(resultSet.getString("img"));
                m.setLength(resultSet.getString("length"));

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
                m.setRank(resultSet.getString("rank"));
                m.setRelease(resultSet.getString("release"));
                m.setImg(resultSet.getString("img"));
                m.setLength(resultSet.getString("length"));

            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }

    public void update(MovieDTO m) {
        String query = "UPDATE `movie`.`movie` SET `title` = ?, `content` = ?, `rank` = ?, `release`=?, `img`=?, `length`=? WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, m.getTitle());
            pstmt.setString(2, m.getContent());
            pstmt.setString(3, m.getRank());
            pstmt.setString(4, m.getRelease());
            pstmt.setString(5, m.getImg());
            pstmt.setString(6, m.getLength());

            pstmt.setInt(7, m.getId());

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

    public int countTotalPage() {
        int totalPage = 0;
        String query = "SELECT COUNT(*) FROM `movie`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            totalPage = count / PAGE_SIZE;
            if (count % PAGE_SIZE != 0) {
                totalPage++;
            }

            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPage;
    }



}
