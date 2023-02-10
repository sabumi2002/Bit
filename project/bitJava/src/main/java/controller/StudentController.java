package controller;

import model.StudentDTO;
import util.ScannerUtil;

import java.sql.*;
import java.util.ArrayList;

public class StudentController {
    private Connection connection;

    public StudentController(Connection connection) {
        this.connection = connection;
//        String address = "jdbc:mysql://localhost/basic";
//        String username = "root";
//        String password = "1111";
//
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver"); // 프로그램 내부에서 mysql과 연결하는 드라이버 클래스를 불러들여온것
//            Connection connection = DriverManager.getConnection(address, username, password);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }
    public void insert(StudentDTO studentDTO) {
        String query = "INSERT INTO `student`(`name`, `korean`, `english`, `math`)VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, studentDTO.getName());
            pstmt.setInt(2, studentDTO.getKorean());
            pstmt.setInt(3, studentDTO.getEnglish());
            pstmt.setInt(4, studentDTO.getMath());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("INSERT ERROR!!!");
            e.printStackTrace();
        }
    }
    public ArrayList<StudentDTO>selectAll(){
        ArrayList<StudentDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `student`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                StudentDTO s = new StudentDTO();
                s.setId(resultSet.getInt("id"));
                s.setName(resultSet.getString("name"));
                s.setKorean(resultSet.getInt("korean"));
                s.setEnglish(resultSet.getInt("english"));
                s.setMath(resultSet.getInt("math"));
                list.add(s);
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("SELECTALL ERROR!!!");
            e.printStackTrace();
        }

        return list;
    }
    public StudentDTO selectOne(int id){
        String query = "SELECT * FROM `student` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                StudentDTO s = new StudentDTO();
                s.setId(resultSet.getInt("id"));
                s.setName(resultSet.getString("name"));
                s.setKorean(resultSet.getInt("korean"));
                s.setEnglish(resultSet.getInt("english"));
                s.setMath(resultSet.getInt("math"));

                resultSet.close();
                pstmt.close();

                return s;
            }

        } catch (SQLException e) {
            System.out.println("SELECTONE ERROR!!!");
            e.printStackTrace();
        }
        return null;
    }
    public void update(StudentDTO s){
        String query = "UPDATE `student` SET `korean` = ?, `english` = ?, `math` = ? WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, s.getKorean());
            pstmt.setInt(2, s.getEnglish());
            pstmt.setInt(3, s.getMath());
            pstmt.setInt(4, s.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("UPDATE ERROR!!!");
            e.printStackTrace();
        }
    }
    public void delete(int id){
        String query = "DELETE FROM `student` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("DELETE ERROR!!!");
            e.printStackTrace();
        }
    }
}
