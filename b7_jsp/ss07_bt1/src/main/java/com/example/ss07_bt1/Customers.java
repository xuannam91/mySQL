package com.example.ss07_bt1;

public class Customers {
    private int customerID;
    private String customerName;
    private int age;

    public Customers() {
    }

    public Customers(int customerID, String customerName, int age) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.age = age;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
