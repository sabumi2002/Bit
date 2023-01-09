package day0109;
// 사용자로부터 번호, 이름, 국어, 영어, 수학 점수를 입력 받아서
// 각각의 정보를 다음과 같이 출력되는 프로그램을 작성하시오.
// 단, 입력에 관한 메소드, 출력에 관한 메소드, 총점 및 평균을 계산 하는 메소드를 따로 분리하시오.

import java.util.Scanner;

// 출력방법:
// 번호: ###번 이름: ###
// 국어: ##점 영어: ##점 수학: ##점
// 총점: ##점 평균: ##.######점
public class Ex02Gradebook {
    public static void main(String[] args) {
        String[] sol = sol();
        printSol(sol);

    }

    public static String[] sol(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("번호 이름 국어 영어 수학 점수를 입력하시오.ex: 1 홍사범 90 90 90");
        System.out.print("> ");
        String[] string1 = new String[7];

        string1[0] = scanner.next();
        string1[1] = scanner.next();
        string1[2] = scanner.next();
        string1[3] = scanner.next();
        string1[4] = scanner.next();
//        string1[0]=kor+eng+math;
//        float avg= (float)sum/3;
        scanner.close();

        return string1;
    }
    public static void printSol(String[] sol){
        int sum= Integer.parseInt(sol[2]) +Integer.parseInt(sol[3])+Integer.parseInt(sol[4]);
        float avg = (float)sum/3;

        System.out.printf("번호: %3d 이름: %3s\n", Integer.parseInt(sol[0]), sol[1]);
        System.out.printf("국어: %2d 영어: %2d 수학: %2d\n",  Integer.parseInt(sol[2]),
                                                            Integer.parseInt(sol[3]),
                                                            Integer.parseInt(sol[4]));
        System.out.printf("총점: %2d 평균: %2.5f\n", sum, avg);
    }

}
