package com.training.domains;

public class Customer {

    private long customerNumber;

    private String customerName;

    private double amountDue;

    public Customer() {
        super();
    }

    public Customer(long customerNumber, String customerName, double amountDue) {
        super();
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.amountDue = amountDue;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", customerName='" + customerName + '\'' +
                ", amountDue=" + amountDue +
                '}';
    }
}
