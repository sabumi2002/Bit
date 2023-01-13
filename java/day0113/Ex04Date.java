package day0113;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex04Date {
    public static void main(String[] args) {
        Date d = new Date();
        d.setHours(16);
        d.setHours(1);

        System.out.println(d);
        DateFormat df = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
        // Y : 연도
        // M : 월
        // d : 해당 월의 일자

        // h: 오전/오후 체계의 현재 시간
        // H: 24시간 체계의 현재시간
        // n: 현재 분
        // s: 현재 초
        // S: 현재 밀리초
        System.out.println(df.format(d));

        df = new SimpleDateFormat("yyMMDD");    // 앞에있는 6글자까지만 대입 시킴 ""사이의 개수만큼
        df.setLenient(false);   //Lenient 관대한, 알아서 변환해줄게~,   false: 변환x
        String temp = "230193";

        try {    // 내가 메소드를 만들때 어떠한 익셉션이 발생할수있다를 알려준다
            // 내가 만든 스트링 변수가 지정한 정수로 변환이 안될경우 에러가 발생
            d = df.parse(temp);

        } catch (Exception e) {    // 해당 익셉션이 발생했을때 문제를 처리할 코드를 등록하라
            // e.printStackTrace();   우리가 늘 보는 빨간에러로 알려줌
            System.out.println("해당 스트링은 date로 변활할수 없어서 현재 날짜로 date를 설정하겠습니다.");
            d = new Date();
        }
        System.out.println(d);
    }
}
