package day0110;

public class Student {
    public int id;
    public String name;
    public int korean;
    public int english;
    public int math;




    public Student(){
        this.id = 0;
        this.name = "";
        this.korean = 0;
        this.english = 0;
        this.math = 0;
    }
    public Student(int id, String name, int korean, int english, int math){
        this.id = id;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }
    public static void printInfo(Student s) {
        System.out.printf("번호: %d번 이름: %s\n", s.id, s.name);
        System.out.printf("국어: %d점 영어: %d점 수학: %d점\n", s.korean, s.english, s.math);
        System.out.printf("총점: %d점 평균: %.2f점\n", calculateSum(s), calculateAverage(s));
    }
    public static int calculateSum(Student s) {
        return s.korean + s.english + s.math;
    }

    public static double calculateAverage(Student s) {
        return (double) calculateSum(s) / 3;
    }

}
