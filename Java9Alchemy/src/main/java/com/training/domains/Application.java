package com.training.domains;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.*;

public class Application {

    public static void main(String[] args) {
        List<Customer> custList = new ArrayList<>();
        Customer ram = new Customer(101, "Ram", 4500);
        Customer shyam = new Customer(102, "Shyam", 14500);
        Customer gan = new Customer(103, "Gan", 2400);
        Customer amit = new Customer(104, "Amit", 5200);
        Customer gaurav = new Customer(105, "Gaurav", 3500);

        //Below List.of is available from Java 9
        custList = List.of(ram, shyam, gan, amit, gaurav);

        //custList.forEach(System.out::println);
        //Another way to do the above is as below
        Consumer displayList = (customer -> System.out.println(customer));
        custList.forEach(displayList);
    }

}
