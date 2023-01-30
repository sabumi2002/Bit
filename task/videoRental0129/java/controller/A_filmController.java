package controller;

import java.sql.Connection;

public class A_filmController {
    // 영화id, 제목, 줄거리, 개봉연도, 개봉언어, 상영길이,  특징, 카테고리
    Connection connection;

    A_filmController(Connection connection){
        this.connection = connection;
    }




}
