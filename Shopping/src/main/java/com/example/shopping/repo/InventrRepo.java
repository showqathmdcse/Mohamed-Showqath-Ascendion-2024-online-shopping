package com.example.shopping.repo;

import com.example.shopping.dto.Inventry;
import com.example.shopping.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventrRepo extends JpaRepository<Inventry,Integer> {
    public List<Inventry> findByStockStaus(String status);
    public Inventry findByProduct(Product product);

}
