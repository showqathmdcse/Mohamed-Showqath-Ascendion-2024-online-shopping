package com.example.shopping.service;

import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.dto.Cart;
import com.example.shopping.dto.Customer;

import java.util.List;

public interface CartService {

    public Cart add(Cart c);
    public Cart update(Cart c);
    public String delete(int custId) throws CustomerNotFoundException;
    public List<Cart> display();
    public Cart findByCustomer(int custId) throws CustomerNotFoundException;


}
