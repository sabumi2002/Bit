package utill;

import java.util.Scanner;

public class ScannerUtill {
    public static void printMessage(String massage) {
        System.out.println(massage);
        System.out.print(">");
    }

    public static String nextLine(Scanner scanner, String message) {
        printMessage(message);
        String temp = scanner.nextLine();
        if (temp.isEmpty()) {
            temp = scanner.nextLine();

        }
        return temp;
    }

    public static int nextInt(Scanner scanner, String message) {
        String temp = nextLine(scanner, message);
        while (!temp.matches("\\d+")) {
            System.out.println("잘못 입력하셨습니다.");
            temp = nextLine(scanner, message);
        }
        return Integer.parseInt(temp);
    }

    public static int nextInt(Scanner scanner, String message, int MIN, int MAX) {
        int temp = nextInt(scanner, message);
        while (temp < MIN || temp > MAX) {
            System.out.println("잘못 입력하셨습니다.");
            temp = nextInt(scanner, message);
        }
        return temp;
    }
}
