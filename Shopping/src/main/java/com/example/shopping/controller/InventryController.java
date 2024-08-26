package com.example.shopping.controller;

import com.example.shopping.dto.Inventry;
import com.example.shopping.dto.Product;
import com.example.shopping.repo.ProdRepo;
import com.example.shopping.service.InventryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop/Inventry")
public class InventryController {

    @Autowired
    ProdRepo pr;

    @Autowired
    InventryService is;

    @PostMapping("/add/{prodId}")
    public Inventry add(@RequestBody Inventry n, @PathVariable("prodId") int prodId){
       Product po=new Product();
       po.setProdId(prodId);
        n.setProduct(po);
        return is.save(n);
    }

    @GetMapping("/viewbyid")
    public Inventry findbyid(@RequestParam("invId")int id){
        return is.findById(id);
    }

    @GetMapping("/DisplayAll")
    public List<Inventry> findAll(){
        return is.findAll();
    }

    @DeleteMapping("/deletebyid")
    public void deletebyid(@RequestParam("invId")int id){
        is.delete(id);
    }




}
