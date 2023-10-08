package jiya;

public class IfElseExample {

    public static void main(String[] args) {
        System.out.println("Program Starts");

        int a = 50, b = 100, c = 200;

//        if (a > b) {
//            System.out.println("Yes");
//        }

//        if (c > a && c > b) { //if ((c > a) && (c > b)) {
//            System.out.println("C is greater than both a and b");
//        }

//        if (a > b) {
//            System.out.println("a is greater than b");
//        } else {
//            System.out.println("b is greater than a");
//        }

        //int a = 50, b = 100, c = 200;
//        if (a > b) {
//            System.out.println("a is greater than b");
//        } else if (b > c) {
//            System.out.println("b is greater than c");
//        } else if (c > b) {
//            System.out.println("c is greater than b");
//        } else {
//            System.out.println("none of the conditions were true");
//        }

        if (a > b) {
            System.out.println("a is greater than b");
        } else if (b > c) {
            System.out.println("b is greater than c");
        } else if (false) {
            System.out.println("c is greater than b");
        }
        System.out.println("Program Ends");
    }

}
