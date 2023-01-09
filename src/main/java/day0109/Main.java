package day0109;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lineNumber = scanner.nextInt();

        int totalHeight = 2 * lineNumber - 1;
        for (int i = 1; i <= totalHeight; i++) {
            String stars = "";
            if (i == 1 || i == totalHeight) {
                for (int j = 1; j <= totalHeight; j++) {
                    stars += "*";
                }
            } else {
                int spaceWidth = 0;

                if (i < lineNumber) {
                    spaceWidth = i - 1;
                } else {
                    int lowerI = totalHeight - i;
                    spaceWidth = lowerI;
                }

                int starWidth = totalHeight - 2 * spaceWidth;

                for (int j = 1; j <= spaceWidth; j++) {
                    stars += " ";
                }

                for (int j = 1; j <= starWidth; j++) {
                    stars += "*";
                }

            }
            System.out.println(stars);
        }
        scanner.close();
    }

}
