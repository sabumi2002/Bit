package day0111;

public class Ex01Gradebook01 {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("조재영");
        s1.setKorean(90);
        s1.setEnglish(40);
        s1.setMath(83);

        s1.print();

        Student s2 = new Student();
        s2.setId(1);
        s2.setName("조재영");
        s2.setKorean(90);
        s2.setEnglish(40);
        s2.setMath(83);

        s2.print();

        Student s3 = new Student();
        s3.setId(1);
        s3.setName("조재영");
        s3.setKorean(90);
        s3.setEnglish(40);
        s3.setMath(83);

        s3.print();

        System.out.println(s2.equals(s3));
    }
}
