package controller;

import model.AddressDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddressController {
    private Connection connection;

    public AddressController(Connection connection) {
        this.connection = connection;
    }

    public AddressDTO selectOne(int id) {
        AddressDTO addressDTO = new AddressDTO();
        String query = "SELECT * FROM `address` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                addressDTO.setId(resultSet.getInt("id"));
                addressDTO.setAddress(resultSet.getString("address"));
                addressDTO.setCity_id(resultSet.getInt("city_id"));
                addressDTO.setPostal_code(resultSet.getString("postal_code"));

            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressDTO;
    }
    public ArrayList<AddressDTO> selectAll(){
        ArrayList<AddressDTO> list = new ArrayList<>();
        String query = "SELECT * FROM `address`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setId(resultSet.getInt("id"));
                addressDTO.setAddress(resultSet.getString("address"));
                addressDTO.setCity_id(resultSet.getInt("city_id"));
                addressDTO.setPostal_code(resultSet.getString("postal_code"));

                list.add(addressDTO);
            }



            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

}


















