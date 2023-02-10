package day0111;

import util.ScannerUtil;

import java.util.Scanner;

public class Student {
//    public static final Scanner SCANNER = new Scanner(System.in);
    private int id;
    private String name;

    private int korean;

    private int english;
    private int math;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorean() {
        return korean;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public Student(){

    }
    public Student(int id, String name, int korean, int english, int math){
        this.id = id;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;

    }
    public boolean findNull() {
        if (this.name == null){
            return true;
        }
            return false;
    }


    public void print() {
        System.out.printf("번호: %d번 이름: %s\n", id, name);
        System.out.printf("국어: %d점 영어: %d점 수학: %d점\n", korean, english, math);
        System.out.printf("총점: %d점 평균: %.2f점\n", calculateSum(), calculateAvg());
    }

    private int calculateSum() {
        return korean + english + math;
    }

    private double calculateAvg() {
        return (double) calculateSum() / 3;
    }

    public boolean equals(Object object){
        if(object instanceof Student){ // instanceof 객체 타입을 확인하는 연산자
            Student s = (Student) object;
            return id == s.id;

        }
        return false;
    }
}

