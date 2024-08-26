package com.example.shopping.service;

import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.dto.Customer;
import com.example.shopping.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerImpl implements CustomerService{

    @Autowired
    CustomerRepo cr;

    @Override
    public Customer add(Customer c) {
        return cr.save(c);
    }

    @Override
    public Customer update(Customer c) {
        return cr.save(c);
    }

    @Override
    public void deletByid(int id) {
        cr.deleteById(id);
    }

    @Override
    public Customer findById(int id) throws CustomerNotFoundException {
        if(cr.findById(id).isPresent())
        return cr.findById(id).get();
        else
            throw new CustomerNotFoundException("Customer not found");
    }

    @Override
    public List<Customer> displayAll() {
        return cr.findAll();
    }

    @Override
    public List<Customer> findByName(String name) {
        return cr.findBycustName(name);
    }

    @Override
    public List<Customer> findByCity(String city) {
        return cr.findByCustCity(city);
    }

    @Override
    public List<Customer> findByCustPincode(int pincode) {
        return cr.findByCustPincode(pincode);
    }
}
