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

        List<Customer> pendingAmtGrtThan5k = custList.parallelStream().filter(customer -> customer.getAmountDue() > 5000).collect(Collectors.toList());
        pendingAmtGrtThan5k.forEach(System.out::println);

        //forEach is also a terminal operation
        custList.parallelStream().filter(customer -> customer.getAmountDue() < 5000).forEach(System.out::println);

        //Task: List just names of customers whose ID is greater than 103
        custList.parallelStream().filter(customer -> customer.getCustomerNumber() > 103).map(customer -> customer.getCustomerName()).forEachOrdered(System.out::println);

        MyConsumer<Customer> funcRef = (list) -> {
            list.forEach(System.out::println);
        };
        System.out.println(funcRef);

        System.out.println("Using Custom Functional Interface");
        funcRef.show(custList);
    }

}
