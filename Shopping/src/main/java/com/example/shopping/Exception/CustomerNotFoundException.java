package com.example.shopping.Exception;

public class CustomerNotFoundException extends Exception{
    private int custId;

    public CustomerNotFoundException(int custId) {
        this.custId = custId;
    }

    public CustomerNotFoundException(String message){}

    @Override
    public String toString() {
        return "CustomerNotFoundException{" +
                "custId=" + custId +
                '}';
    }
}
