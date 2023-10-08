package jiya;

public class Loops {

    public static void main(String[] args) {
        String[] names = new String[6];
        names[0] = "Jiya";
        names[1] = "Jida";
        names[2] = "Jiza";
        names[3] = "Jiva";
        names[4] = "Jiga";
        names[5] = "Jagreehi";

//        System.out.println("Names are:");
//        System.out.println(names[0]);
//        System.out.println(names[1]);
//        System.out.println(names[2]);
//        System.out.println(names[3]);
//        System.out.println(names[4]);

        /*
        int arraySize = names.length;
        int i = 0;

        System.out.println("Names from while loop are:");
        while(i < arraySize) {
            System.out.println(names[i]);
            i++; // i = i + 1;
        }


        System.out.println("Names from do-while loop are");
        do {
            System.out.println(names[i]);
            i++; // i = i + 1;
        } while(false);
        */

        //WAP to print the first 10 natural numbers
//        for (int j = 1; j <= 10; j++) {
//            System.out.println(j);
//        }

//        int k = 0;
//        int x = 0;
//        for (; k <= 3; k++,x++) {
//            System.out.println(k);
//            System.out.println(x);
//        }

        for (int k = 0,x = 0; k <= 3 && x<=3; k++,x++) {
            System.out.println(k);
            System.out.println(x);
        }

    }
}
