package com.example.shopping.repo;

import com.example.shopping.dto.Cart;
import com.example.shopping.dto.CartProduct;
import com.example.shopping.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CartRepo extends JpaRepository<Cart,Integer>{
    public Cart findByCustomer(Customer customer);


}
