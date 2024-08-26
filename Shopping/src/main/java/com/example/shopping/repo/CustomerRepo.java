package com.example.shopping.repo;

import com.example.shopping.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    public List<Customer> findBycustName(String name);
    public List<Customer> findByCustCity(String city);
    public List<Customer> findByCustPincode(int pincode);

}


