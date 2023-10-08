package jiya;

public class Patterns {

    public static void main(String[] args) {

        //Solution for 15A
        /*
        1
        2 1
        3 2 1
        4 3 2 1
        5 4 3 2 1
        */

        for (int i = 1; i <= 5; i++) { //Rows
            for (int j = i; j >= 1; j--) { //Columns
                System.out.print(j + " ");
            }
            System.out.println();
        }

        //Solution for 15A
        /*
        1 2 3 4 5
        1 2 3 4
        1 2 3
        1 2
        1
        */

        for (int k = 1; k <= 5; k++) {
            for (int l = 1; l <= k; l++) {
                System.out.println(l + " ");
            }
            System.out.println();
        }
    }
}
