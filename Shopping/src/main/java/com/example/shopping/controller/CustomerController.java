package com.example.shopping.controller;


import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.dto.Cart;
import com.example.shopping.dto.Customer;
import com.example.shopping.service.CartService;
import com.example.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/customer")
public class CustomerController {

    @Autowired
    CustomerService cs;

    @Autowired
    CartService cd;

    @PostMapping("/add")
    public Customer add(@RequestBody Customer c){
        Cart cart = new Cart();
        cart.setCustomer(c);
        cd.add(cart);
        return cs.add(c);

    }

    @PutMapping("/update")
    public Customer update(@RequestBody Customer c){
        return cs.add(c);
    }

    @DeleteMapping("/deletbyid/{custId}")
    public void delete(@PathVariable("custId") int id){
        cs.deletByid(id);
    }

    @GetMapping("/id/{custId}")
    public Customer findById(@PathVariable("custId") int id) throws CustomerNotFoundException {
        return cs.findById(id);
    }

    @GetMapping("/name/{custName}")
    public List<Customer> findByName(@PathVariable("custName") String name){
        return cs.findByName(name);
    }

    @GetMapping("/city/{custCity}")
    public List<Customer> displayAll(@PathVariable("custCity")String city){
        return cs.findByCity(city);
    }

    @GetMapping("/pincode/{custPincode}")
    public List<Customer> findByPincode(@PathVariable("custPincode")int pincode){
        return cs.findByCustPincode(pincode);
    }


}
