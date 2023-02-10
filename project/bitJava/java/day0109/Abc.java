package day0109;

import java.util.Scanner;

public class Abc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("별찍기 1번");
        System.out.println("출력할 줄 수 입력");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();
//
//        for (int i = 1; i <= lineNumber; i++){
//            for (int j = 1; j<=i; j++){
//                System.out.print("*");
//            }
//            System.out.println();
//        }


        String s="";
//        for(int i=0; i<5;i++){
//            s = s +'*';
//            System.out.println(s);
//        }
//
//        s= "";
//        for(int i=lineNumber; i>0;i--){
//            for(int j=i; j>0; j--){
//                s = s + '*';
//            }
//            System.out.println(s);
//            s= "";
//        }
//
//        s= "";
//        for(int i=0; i<lineNumber; i++){
//            for(int j=lineNumber-i; j>1; j--){
//                s= s+" ";
//            }
//            for(int j=0; j<=i; j++){
//                s= s+"*";
//            }
//            System.out.println(s);
//            s= "";
//        }

//        s= "";
//        for(int i=0; i<lineNumber; i++){
//            for(int j=0; j<i; j++){
//            s= s+" ";
//            }
//            for(int j=lineNumber-i; j>0; j--){
//                s= s+"*";
//            }
//            System.out.println(s);
//            s= "";
//        }


//        System.out.println("별찍기 5번");
//        s= "";
//        for(int i=1; i<=lineNumber; i++){
//            for(int j=lineNumber-i; j>1; j--){
//                s= s+" ";
//            }
//            for(int j=0; j<=i; j++){
//                s= s+"*";
//            }
//            for(int j=1; j<=i;j++){
//                s+= "*";
//            }
//            System.out.println(s);
//            s= "";
//        }
//        System.out.println("별찍기 6번");
//        s= "";
//        for(int i=0; i<lineNumber; i++){
//            for(int j=0; j<i; j++){
//                s= s+" ";
//            }
////            -2*i+(2*lineNumber+1)
////            2*(lineNumber-i)+1
//            for(int j=1; j<=2*(lineNumber-i)+1; j++){
//                s= s+"*";
//            }
//
//            System.out.println(s);
//            s= "";
//        }

//        System.out.println("별찍기 10번");
//        s= "";
//        for(int i=0; i<lineNumber; i++){
//
//            for(int j=lineNumber-i; j>0; j--){
//                s= s+"*";
//            }
//            for(int j=0; j<i; j++){
//                s= s+" ";
//            }
//            for(int j=1; j<i; j++){
//                s= s+" ";
//            }
//            for(int j=lineNumber-i; j>0; j--){
//                s= s+"*";
//            }
//            if (i==0) {
//                s= "";
//                for(int j=0; j<lineNumber*2-1;j++){
//                    s+="*";
//                }
//            }
//
//
//            System.out.println(s);
//            s= "";
//        }
//        s= "";
//        for(int i=2; i<lineNumber; i++){
//            for(int j=0; j<i; j++){
//                s= s+"*";
//            }
//            for(int j=lineNumber-i; j>0; j--){
//                s= s+" ";
//            }
//            for(int j=lineNumber-i; j>1; j--){
//                s= s+" ";
//            }
//            for(int j=0; j<i; j++){
//                s= s+"*";
//            }
//
//            System.out.println(s);
//            s= "";
//
//        }
//        for(int j=0; j<lineNumber*2-1;j++){
//            s+="*";
//        }
//        System.out.println(s);


//        System.out.println("별찍기 9번");
//        String stars;
//        for(int i = 0; i<lineNumber;i++){
//            stars = "";
//            for(int j = 1; j <= lineNumber - i; j++){
//                stars +=" ";
//            }
//            for (int j=1; j<=2*i-1;j++){
//                stars +="*";
//            }
//            System.out.println(stars);
//        }
//        stars="";
//        for(int i=0; i<lineNumber; i++){
//            for(int j=0; j<i; j++){
//                stars +=" ";
//            }
////            -2*i+(2*lineNumber+1)
////            2*(lineNumber-i)+1
//            for(int j=1; j<=2*(lineNumber-i)-1; j++){
//                stars +="*";
//            }
//
//            System.out.println(stars);
//            stars= "";
//        }


        //solution
        //별찍기5번
//        for(int i = lineNumber; i>-1;i--){
//            String stars = "";
//            for(int j = 1; j <= lineNumber - i; j++){
//                stars +=" ";
//            }
//            for (int j=1; j<=2*i-1;j++){
//                stars +="*";
//            }
//            System.out.println(stars);
//        }


        //별찍기 10번
        int totalHeight = 2*lineNumber-1;

        for (int i = 1; i<=totalHeight;i++){
            String stars = "";
            if(i<lineNumber){
                for (int j=1; j<=lineNumber-i; j++){
                    stars+=" ";
                }
                for(int j=1; j<= 2*i-1;j++){
                    stars+= "*";
                }
            } else {
                int lowerI = i-lineNumber+1;
                for (int j=1; j<=lowerI-1; j++){
                    stars+=" ";
                }
                for(int j=1; j<= 2*(lineNumber-lowerI)+1;j++){
                    stars+= "*";
                }
            }
            System.out.println(stars);

        }
    }
}


