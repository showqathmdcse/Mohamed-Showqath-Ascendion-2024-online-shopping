package com.example.shopping.repo;

import com.example.shopping.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepo extends JpaRepository<Product,Integer> {
    public List<Product> findByProdName(String name);
    public List<Product> findProductByProdCategory(String catag);
    public List<Product> findProductByProdPriceAndProdName(int price,String name);
    public List<Product> findProductByProdNameContainsIgnoreCase(String name);
}
