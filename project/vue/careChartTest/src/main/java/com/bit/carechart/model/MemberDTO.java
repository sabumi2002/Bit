package com.bit.carechart.model;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDTO {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private Date entryDate;
}
