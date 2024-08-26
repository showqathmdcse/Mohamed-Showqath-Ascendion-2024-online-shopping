package com.example.shopping.service;

import com.example.shopping.dto.Order;
import com.example.shopping.repo.OrderRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl  implements OrderService{

    @PersistenceContext

    @Autowired
    private EntityManager em;

    @Autowired
    OrderRepo or;

    @Override
    public Order save(Order order) {
        return or.save(order);
    }

    @Override
    public List<Order> getOrderByCustId(int custId) {
        String sql = "SELECT * FROM placed WHERE cust_id ="+"cust_id";
        Query q = em.createNativeQuery(sql, Order.class);
        List<Order> orders = q.getResultList();
        return orders;
    }

}
