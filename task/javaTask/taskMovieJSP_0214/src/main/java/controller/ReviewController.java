package controller;

import connector.ConnectionMaker;
import model.ReviewDTO;

import javax.crypto.spec.PSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewController {
    private Connection connection;

    public ReviewController(ConnectionMaker connectionMaker) {
        this.connection = connectionMaker.makeConnection();
    }

    public void insert(ReviewDTO r) {
        String query = "INSERT INTO `movie`.`review`(`writer_id`, `movie_id`, `rating`, `review`, `entry_date`, `modify_date`) " +
                "VALUES(?, ?, ?, ?, NOW(), NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, r.getWriterId());
            pstmt.setInt(2, r.getMovieId());
            pstmt.setInt(3, r.getRating());
            pstmt.setString(4, r.getReview());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ReviewDTO> selectAll(int movieId) {
        ArrayList<ReviewDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `movie`.`review` WHERE `movie_id`=? ORDER BY `id` DESC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, movieId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                ReviewDTO r = new ReviewDTO();
                r.setId(resultSet.getInt("id"));
                r.setWriterId(resultSet.getInt("writer_id"));
                r.setMovieId(resultSet.getInt("movie_id"));
                r.setRating(resultSet.getInt("rating"));
                r.setReview(resultSet.getString("review"));
                r.setEntryDate(resultSet.getTimestamp("entry_date"));
                r.setModifyDate(resultSet.getTimestamp("modify_date"));

                list.add(r);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ReviewDTO selectOne(int id) {
        ReviewDTO r = null;
        String query = "SELECT * FROM `movie`.`review` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                r = new ReviewDTO();
                r.setId(resultSet.getInt("id"));
                r.setWriterId(resultSet.getInt("writer_id"));
                r.setMovieId(resultSet.getInt("movie_id"));
                r.setRating(resultSet.getInt("rating"));
                r.setReview(resultSet.getString("review"));
                r.setEntryDate(resultSet.getTimestamp("entry_date"));
                r.setModifyDate(resultSet.getTimestamp("modify_date"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    public void update(ReviewDTO r) {
        String query = "UPDATE `movie`.`review` SET `rating` = ?, `review` = ?, `modify_date` = NOW() WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, r.getRating());
            pstmt.setString(2, r.getReview());
            pstmt.setInt(3, r.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM `movie`.`review` WHERE `id` = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double movieAvg(int movieId) {
        ArrayList<ReviewDTO> list = selectAll(movieId);
        int sum = 0;
        for (ReviewDTO r : list) {
            sum += r.getRating();
        }

        double avg = (double) sum / list.size();
        return Math.ceil(avg*100)/100.0;
    }
}
