package model;

import lombok.Data;

@Data
public class UserDTO {
    int id;
    String username;
    String password;
    String nickname;
    int rank;

}
