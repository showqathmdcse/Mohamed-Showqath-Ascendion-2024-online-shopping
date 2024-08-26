package com.example.shopping.service;

import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.dto.Customer;

import java.util.List;

public interface CustomerService {
    public Customer add(Customer c);
    public Customer update(Customer c);
    public void deletByid(int id);
    public Customer findById(int id) throws CustomerNotFoundException;
    public List<Customer> displayAll();
    public List<Customer> findByName(String name);
    public List<Customer> findByCity(String city);
    public List<Customer> findByCustPincode(int pincode);
}
