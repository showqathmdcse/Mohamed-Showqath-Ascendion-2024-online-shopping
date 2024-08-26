package com.example.shopping.service;

import com.example.shopping.Exception.ProductNotFoundException;
import com.example.shopping.dto.Product;

import java.util.List;

public interface ProductService {

    public Product create(Product p);
    public Product update(Product p);
    public void delete(int id);
    public List<Product> displayAll();
    public Product findById(int id)throws ProductNotFoundException;

    public List<Product> findByProdName(String name);
    public List<Product> findProductByProdCategory(String catag);
    public List<Product> findProductByProdPriceAndProdName(int price,String name);
    public List<Product> findProductByProdNameContainsIgnoreCase(String name);
}
