package day0110;

import util.ScannerUtil;

import java.util.Scanner;

// 무한루프를 사용하여
// 사용자가 입력을 누를 때마다 새로운 학생의 정보가 입력이 되고
// 출력을 누를때마다 맨 마지막으로 입력한 학생의 정보가 출력되는 프로그램을
// 작성해보시오.
public class Ex04Gradebook02 {
    /*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeSol emp = new EmployeeSol();

        while (true){

            String message = "입력 또는 출력을 입력해주세요.";
            String q = ScannerUtil.nextLine(scanner, message);


            if (q.equals("입력")) {
                insertInfo(scanner, emp);

            }else if(q.equals("출력")) {
                printInfo(emp);
            }

        }
    }
    public static void insertInfo(Scanner scanner, EmployeeSol employee){
        String message;
        // 사원 번호 입력
        message = "사원의 번호를 입력해주세요.";
        employee.id = ScannerUtil.nextInt(scanner, message);
        // 사원 이름 입력;
        message = "사원의 이름을 입력해주세요.";
        employee.name = ScannerUtil.nextLine(scanner, message);
        // 사원 직급 입력;
        message = "사원의 직급을 입력해주세요.";
        employee.rank = ScannerUtil.nextLine(scanner, message);
        // 사원 부서 입력;
        message = "사원의 부서을 입력해주세요.";
        employee.dept = ScannerUtil.nextLine(scanner, message);
        // 사원 연봉 입력;
        message = "사원의 연봉을 입력해주세요.";
        employee.salary = ScannerUtil.nextInt(scanner, message);

    }
    public static void printInfo(EmployeeSol employeeSol){
        System.out.println("-----------------------------------");
        System.out.println("사원 번호: " + employeeSol.id);
        System.out.println("사원 이름 " + employeeSol.name);
        System.out.println("사원 부서: "+ employeeSol.dept);
        System.out.println("사원 직급: "+ employeeSol.rank);
        System.out.println("사원 연봉: "+ employeeSol.salary);
        System.out.println("-----------------------------------");
    }
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student s = new Student();
        boolean inputSwitch = false;

        while (true) {
            String message = "1. 입력 2. 출력 3. 종료";

            int userChoice = ScannerUtil.nextInt(scanner, message);

            if (userChoice == 1) {
                insertInfo(scanner, s);
                inputSwitch = true;
            } else if (userChoice == 2) {
                if(inputSwitch){
                    printInfo(s);
                }else{
                    System.out.println("아직 입력된 학생의 정보가 존재하지않습니다.");
                }
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }

        }
        scanner.close();
    }

    public static void insertInfo(Scanner scanner, Student s) {
        String message;

        message = "학생의 번호를 입력해주세여.";
        s.id = ScannerUtil.nextInt(scanner, message);

        message = "학생의 이름을 입력해주세요.";
        s.name = ScannerUtil.nextLine(scanner, message);

        message = "학생의 국어 점수를 입력해주세요.";
        s.korean = ScannerUtil.nextInt(scanner, message);

        message = "학생의 영어 점수를 입력해주세요.";
        s.english = ScannerUtil.nextInt(scanner, message);

        message = "학생의 수학 점수를 입력해주세요.";
        s.math = ScannerUtil.nextInt(scanner, message);

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
