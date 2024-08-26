package com.example.shopping.service;

import com.example.shopping.Exception.ProductNotFoundException;
import com.example.shopping.dto.Product;
import com.example.shopping.repo.ProdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdImpl implements ProductService{

    @Autowired
    ProdRepo pr;

    @Override
    public Product create(Product p) {
        return pr.save(p);
    }

    @Override
    public Product update(Product p) {
        return pr.save(p);
    }

    @Override
    public void delete(int id) {
       pr.deleteById(id);
    }

    @Override
    public List<Product> displayAll() {
        return pr.findAll();
    }

    @Override
    public Product findById(int id) throws ProductNotFoundException {
        if(pr.findById(id).isPresent())
        return pr.findById(id).get();
        else
            throw new ProductNotFoundException("Product not found");
    }

    @Override
    public List<Product> findByProdName(String name) {
        return pr.findByProdName(name);
    }

    @Override
    public List<Product> findProductByProdCategory(String catag) {
        return pr.findProductByProdCategory(catag);
    }

    @Override
    public List<Product> findProductByProdPriceAndProdName(int price, String name) {
        return pr.findProductByProdPriceAndProdName(price,name);
    }

    @Override
    public List<Product> findProductByProdNameContainsIgnoreCase(String name) {
        return pr.findProductByProdNameContainsIgnoreCase(name);
    }
}
