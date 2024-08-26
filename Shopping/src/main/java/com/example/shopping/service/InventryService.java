package com.example.shopping.service;

import com.example.shopping.dto.Inventry;
import com.example.shopping.dto.Product;

import java.util.List;

public interface InventryService {
    public Inventry save(Inventry in);
    public Inventry upd(Inventry in);
    public void delete(int id);
    public List<Inventry> findByStatus(String status);
//    public Inventry findByProdId(int prodId);
public Inventry findByProduct(Product product);

public Inventry findById(int id);
public List<Inventry> findAll();
















































}
