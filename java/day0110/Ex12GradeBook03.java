package day0110;

import util.ScannerUtil;

import java.util.Scanner;

// 1. 5명의 학생을 성적을 관리하는 프로그램을 작성하시오.
//    단, 5명을 모두 입력한 후에는 더이상 입력할 수 없도록 코드를 작성하시오.
// 2. 5명의 학생을 성적을 관리하는 프로그램을 작성하시오.
//    단, 5명을 모두 입력한 후에 새로운 학생 정보를 입력할 시에는
//    가장 오래된 기록을 제거하고 새로운 학생 정보를 입력되도록 코드를 작성하시오.
public class Ex12GradeBook03 {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int STUDENT_SIZE = 5;

    public static void main(String[] args) {
        Student[] students = new Student[STUDENT_SIZE];

        boolean inputSwitch = false;
        int studentCount=0;




        while (true) {
            String message = "1. 입력 2. 출력 3. 종료";

            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) { // 한명씩 입력을 받는데 5명이 넘어가면 더이상 못받아요
                if(STUDENT_SIZE>studentCount){
                    students[studentCount] = new Student();
                    insertInfo(SCANNER, students[studentCount], studentCount);
                    studentCount++;
                    inputSwitch = true;
                }else{
                    System.out.println("5명 인원이 모두 채워졌습니다.");
                }

            } else if (userChoice == 2) { // 출력
                if(inputSwitch){
                    for(int i=0; i< studentCount;i++){
                        printInfo(students[i]);
                    }

                }else{
                    System.out.println("아직 입력된 학생의 정보가 존재하지않습니다.");
                }
            } else if (userChoice == 3) { // 종료
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }

        }
        SCANNER.close();
    }



    public static void insertInfo(Scanner scanner, Student s, int num) {
        String message;

        message = num +"번째 학생의 번호를 입력해주세여.";
        s.id = ScannerUtil.nextInt(scanner, message);

        message = num + "번째 학생의 이름을 입력해주세요.";
        s.name = ScannerUtil.nextLine(scanner, message);

        message = num + "번째 학생의 국어 점수를 입력해주세요.";
        s.korean = ScannerUtil.nextInt(scanner, message);

        message = num + "번째 학생의 영어 점수를 입력해주세요.";
        s.english = ScannerUtil.nextInt(scanner, message);

        message = num + "번째 학생의 수학 점수를 입력해주세요.";
        s.math = ScannerUtil.nextInt(scanner, message);

    }

    public static void printInfo(Student s) {
        System.out.printf("번호: %d번 이름: %s\n", s.id, s.name);
        System.out.printf("국어: %d점 영어: %d점 수학: %d점\n", s.korean, s.english, s.math);
        System.out.printf("총점: %d점 평균: %.2f점\n", calculateSum(s), calculateAverage(s));
        System.out.println("-----------------------------------------");
    }

    public static int calculateSum(Student s) {
        return s.korean + s.english + s.math;
    }

    public static double calculateAverage(Student s) {
        return (double) calculateSum(s) / 3;
    }


    /*
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int STUDENT_SIZE = 5;
    public static Student[] student = new Student[STUDENT_SIZE];

    public static void main(String[] args) {
        for (int i=0; i< STUDENT_SIZE; i++){
            student[i] = new Student();
            insertInfo(SCANNER, i);
            Student.printInfo(student[i]);
        }
//1 홍사범 2 3 4
    }
    public static void insertInfo(Scanner scanner, int i) {
        String message;
        int id;
        String name;
        int korean;
        int english;
        int math;
        Student temp;
        message = "학생의 번호를 입력해주세여.";
        id = ScannerUtil.nextInt(scanner, message);

        message = "학생의 이름을 입력해주세요.";
        name = ScannerUtil.nextLine(scanner, message);

        message = "학생의 국어 점수를 입력해주세요.";
        korean = ScannerUtil.nextInt(scanner, message);

        message = "학생의 영어 점수를 입력해주세요.";
        english = ScannerUtil.nextInt(scanner, message);

        message = "학생의 수학 점수를 입력해주세요.";
        math = ScannerUtil.nextInt(scanner, message);
        temp= new Student(id, name, korean, english, math);

        student[i]= temp;
    }
    */

}
