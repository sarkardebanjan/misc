package jiya;

public class Addition {

    public static void main(String[] args) {

        //WAP to add two number where first number is 10 and second number is 20
        int a = 10;
        int b = 20;
        int sum  = a + b;
        System.out.println("Sum = " + sum);

        System.out.println("Value of max byte = " + Byte.MAX_VALUE);
        System.out.println("Value of max short = " + Short.MAX_VALUE);
        System.out.println("Value of max int = " + Integer.MAX_VALUE);
        System.out.println("Value of max long = " + Long.MAX_VALUE);
        System.out.println("Value of max float = " + Float.MAX_VALUE);
        System.out.println("Value of max double = " + Double.MAX_VALUE);
        System.out.println("Value of max boolean = " + Boolean.TRUE);
        System.out.println("Value of max char = " + (int) Character.MAX_VALUE);

        String name = "Jiya";
        int marksInMaths = 70;

        int[] marks = {10, 20, 30, 40 ,50 ,60};
        int[] marksNew = new int[6];
        marksNew[0] = 10;
        marksNew[1] = 20;
        marksNew[2] = 30;
        marksNew[3] = 40;
        marksNew[4] = 50;
        marksNew[5] = 60;

        System.out.println("Array of marks = " + marksNew.toString());
    }
}
