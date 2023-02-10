package day0111;

import util.ArrayUtil;
import util.ScannerUtil;

import java.util.Scanner;

public class StudentManager {
    public static final Scanner SCANNER = new Scanner(System.in);
    // public static final int STUDENT_SIZE = 5;
    public static Student[] studentArray = new Student[0];
    public static int studentCount = 1;

    public static void main(String[] args) {
        menu();

    }

    public static void menu() {
        String message = "";

        while (true) {
            message = "1. 입력 2. 출력 3. 종료";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) { // 입력
                insertStudent();

            } else if (userChoice == 2) { // 출력
                printStudent();

            } else if (userChoice == 3) { // 종료
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    public static int findIndex() {
        for (int i = 0; i < studentArray.length; i++) {
            if (studentArray[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static void insertStudent() {
        /*
        int index = findIndex();
        if (index == -1){
            index = 4;
            for(int i=0; i< STUDENT_SIZE-1; i++){
                studentArray[i] = studentArray[i+1];
            }
        }
        */

        System.out.println("학생정보를 입력해주세요");
        Student s = new Student();
        String message = "번호를 입력해주세요";
        int id = ScannerUtil.nextInt(SCANNER, message);
        message = "이름을 입력해주세요";
        String name = ScannerUtil.nextLine(SCANNER, message);
        message = "국어 점수를 입력해주세요";
        int korean = ScannerUtil.nextInt(SCANNER, message);
        message = "영어 점수를 입력해주세요";
        int english = ScannerUtil.nextInt(SCANNER, message);
        message = "수학 점수를 입력해주세요";
        int math = ScannerUtil.nextInt(SCANNER, message);
        s = new Student(id, name, korean, english, math);

        studentArray = ArrayUtil.add(studentArray, s); // studentArray[index] = s;


    }

    public static void updateStudent(){
        String message = "수정할 학생 번호를 입력해주세요.";

        int studentId = ScannerUtil.nextInt(SCANNER, message);

        if (ArrayUtil.contains(studentArray, studentId)){
            int index = ArrayUtil.indexOf(studentArray, studentId);
            studentArray[index].setKorean(ScannerUtil.nextInt(SCANNER, "수정할 국어 점수를 입력해주세요."));
            studentArray[index].setEnglish(ScannerUtil.nextInt(SCANNER, "수정할 영어 점수를 입력해주세요."));
            studentArray[index].setMath(ScannerUtil.nextInt(SCANNER, "수정할 수학 점수를 입력해주세요."));
            System.out.println("수정 되었습니다.");
        }else{
            System.out.println("잘못 입력하셨습니다.");
            updateStudent();
        }
    }
    public static void deleteStudent(){
        String message = "삭제할 학생 번호를 입력해주세요.";
        int studentId = ScannerUtil.nextInt(SCANNER, message);

        if (ArrayUtil.contains(studentArray, studentId)){
        studentArray = ArrayUtil.removeByElement(studentArray, studentId);
        System.out.println("삭제되었습니다.");
        }else{
            System.out.println("잘못 입력하셨습니다.");
        }
    }
    public static void printStudent() {
        if (ArrayUtil.isEmpty(studentArray) == true) {
            System.out.println("등록된 학생정보가 없습니다.");
        } else {
            for (int i = 0; i < ArrayUtil.size(studentArray); i++) {
                System.out.println((i + 1) + " 번째 학생정보 입니다.");
                studentArray[i].print();
                System.out.println("----------------------------------");
            }

            String message = "1. 수정 2. 삭제 3. 돌아가기";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {    // 수정
                updateStudent();
            } else if (userChoice == 2) { // 삭제
                deleteStudent();
            } else if (userChoice == 3) { // 돌아가기

            }
        }
    }
}
