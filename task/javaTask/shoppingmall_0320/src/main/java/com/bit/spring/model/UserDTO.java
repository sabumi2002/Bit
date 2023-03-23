package com.bit.spring.model;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String role;
}
