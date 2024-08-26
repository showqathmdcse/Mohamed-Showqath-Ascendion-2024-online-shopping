package com.example.shopping.controller;


import com.example.shopping.Exception.ProductNotFoundException;
import com.example.shopping.dto.Product;
import com.example.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/product")
public class ProductController {

    @Autowired
    ProductService ps;

    @PostMapping("/add")
    public Product create(@RequestBody Product p){

        return ps.create(p);
    }

   @PutMapping("/update")
   public Product update(@RequestBody Product p){
       return ps.create(p);
   }

   @DeleteMapping("/delete/{prodId}")
    public void delete(int id){
        ps.delete(id);
   }

   @GetMapping("/diaplayAll")
    public List<Product> displayall(){
        return ps.displayAll();
   }
   @GetMapping("/name/{prodName}")
    public List<Product> findByname(@PathVariable("prodName")String name){
        return ps.findByProdName(name);
   }
    @GetMapping("/cat/{prodCategory}")
    public List<Product> findBycat(@PathVariable("prodCategory")String name){
        return ps.findProductByProdCategory(name);
    }
    @GetMapping("/under/{prodPrice}/{prodName}")
    public List<Product> findBypriceandname(@PathVariable("prodPrice")int price,@PathVariable("prodName")String name){
        return ps.findProductByProdPriceAndProdName(price, name);
    }
    @GetMapping("/key/{prodName}")
    public List<Product> findBykeyword(@PathVariable("prodName")String name){
        return ps.findProductByProdNameContainsIgnoreCase(name);
    }
    @GetMapping("/id/{prodId}")
    public Product findById(@PathVariable("prodId")int id) throws ProductNotFoundException {
        return ps.findById(id);
    }


}
