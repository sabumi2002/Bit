package day0109;

import util.ScannerUtil;

import java.util.Scanner;

public class Ex02GradebookSol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int id = getId(scanner);
        String name = getName(scanner);

        int korean = getKorean(scanner);
        int english = getEnglish(scanner);
        int math = getMath(scanner);

        printInfo(id, name, korean, english, math);

        scanner.close();
    }

    public static int getId(Scanner scanner){
        String message = "학생의 번호를 입력해주세요.";

        return ScannerUtil.nextInt(scanner, message);
    }

    public static String getName(Scanner scanner){
        String temp;
        String message= "학생의 이름을 입력해주세요.";

        temp = ScannerUtil.nextLine(scanner, message);
        return temp;
    }

    public static int getKorean(Scanner scanner){
        int temp;
        String message;
        message = "학생의 국어 점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while(temp < min || temp > max){
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }

        return temp;
    }
    public static int getEnglish(Scanner scanner){
        int temp;
        String message;
        message = "학생의 영어 점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while(temp < min || temp > max){
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }

        return temp;
    }
    public static int getMath(Scanner scanner){
        int temp;
        String message;
        message = "학생의 수학 점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while(temp < min || temp > max){
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }

        return temp;
    }

    public static void printInfo(int id, String name, int korean, int english, int math){
        System.out.println("번호: " + id +"번 이름: "+ name);
        System.out.printf("국어: %d점 영어: %d점 수학: %d점\n", korean, english, math);
        System.out.printf("총점: %d점 평균: %f점\n", calculateSum(korean, english, math), calculateAverage(korean, english, math));


    }
    public static int calculateSum(int korean, int english, int math){
        return korean + english+ math;
    }
    public static double calculateAverage(int korean, int english, int math){
        final int SUBJECT_SIZE =3;
        return (korean + english + math)/ (double)SUBJECT_SIZE;
    }
}
