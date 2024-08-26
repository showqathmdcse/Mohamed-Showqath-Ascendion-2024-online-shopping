package com.example.shopping.service;

import com.example.shopping.dto.Inventry;
import com.example.shopping.dto.Product;
import com.example.shopping.repo.InventrRepo;
import com.example.shopping.repo.ProdRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvSerImpl implements InventryService {

    @PersistenceContext

    @Autowired
    private EntityManager em;

    @Autowired
    InventrRepo in;


    @Override
    public Inventry save(Inventry n) {
        return in.save(n);
    }

    @Override
    public Inventry upd(Inventry n) {
        return in.save(n);
    }

    @Override
    public void delete(int id) {
         in.deleteById(id);
    }

    @Override
    public List<Inventry> findByStatus(String status) {
        return in.findByStockStaus(status);
    }

    @Override
    public Inventry findByProduct(Product product) {
        return in.findByProduct(product);
    }

    @Override
    public Inventry findById(int id) {
        return in.findById(id).get();
    }

    @Override
    public List<Inventry> findAll() {
        return in.findAll();
    }


}
