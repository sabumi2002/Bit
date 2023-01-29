package model;

import java.util.Date;

public class CustomerDTO {
    // customer
    int id;
    int store_id;   // 자주가는 store
    String first_name;
    String last_name;
    int address_id;
    Date create_date;   // 회원가입 날짜
}
