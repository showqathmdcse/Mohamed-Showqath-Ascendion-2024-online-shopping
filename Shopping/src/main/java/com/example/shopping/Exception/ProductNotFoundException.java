package com.example.shopping.Exception;

public class ProductNotFoundException extends Exception {
    private int prodId;
    public ProductNotFoundException(int prodId) {
        this.prodId = prodId;
    }
    public ProductNotFoundException(String s) {}


    public String toString() {
        return "ProductNotFoundException [prodCode=" + prodId + "]";
    }
}
