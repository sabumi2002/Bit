package model;

import java.util.ArrayList;

public class FilmDTO {
    // film, film_category, category, actor, language
    // 1. 영화번호
    // 2. 영화제목
    // 3. 영화 줄거리
    // 4. 언어
    // 5.
    int id;
    String title;
    String description; // 줄거리
    int language_id;    // 언어
    int rental_duration;    // 대여기간
    int rental_rate; // 대여기간당 대여비용
    int replacement_cost;   // 손상된필름 청구금액

    int category_id;    // 카테고리 id
    ArrayList<Integer> actor_id;    // 출연배우 id

    // String category_name;
    // String language_name;
    // String actor_lastName;
    // String actor_firstName;



}
