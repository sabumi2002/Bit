package day0110;

import util.ScannerUtil;

import java.util.Scanner;

public class Ex03EmpSol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EmployeeSol emp = new EmployeeSol();

        insertInfo(scanner, emp);
        printInfo(emp);

        scanner.close();
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

}
