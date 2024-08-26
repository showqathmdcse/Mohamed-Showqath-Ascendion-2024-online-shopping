package com.example.shopping.Exception;

public class ProductOutOfStockException extends Exception{
    private int quantity;
    public ProductOutOfStockException(String message) {}

    @Override
    public String toString() {
        return "ProductOutOfStockException{" +
                "quantity=" + quantity +
                '}';
    }
}
