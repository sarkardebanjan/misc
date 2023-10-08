package jiya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BufferedReaderExample {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter you name");
        String name1 = in.nextLine();
        System.out.println("Name is: " + name1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter you name");
        String name2 = br.readLine();
        System.out.println("Name is: " + name2);

    }
}
