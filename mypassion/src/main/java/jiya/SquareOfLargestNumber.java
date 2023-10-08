package jiya;

import java.util.Scanner;

public class SquareOfLargestNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 10;
        int b = 5;
        System.out.println("Input the value of a and b");
        a = scanner.nextInt();
        b = scanner.nextInt();

        int greatestNo = a;
        //greatestNo = a > b ? a : b;

        if (a > b) {
            greatestNo = a;
        } else {
            greatestNo = b;
        }

        System.out.println("Greatest No is: " + greatestNo);

        //int result = greatestNo * greatestNo;
        double result = Math.pow(greatestNo, 2);
        System.out.println("Result: " + result);


        String name1  = "God";
        String name2 = "jiya";
        if (name1.equals(name2)) {
            System.out.println("Names are the same");
        } else if (name1.equalsIgnoreCase(name2)) {
            System.out.println("Names are same but in different case");
        } else if (name1.equalsIgnoreCase("God")) {
            System.out.println("Name is GOD");
        } else {
            System.out.println("Names are NOT the same");
        }
    }

}
