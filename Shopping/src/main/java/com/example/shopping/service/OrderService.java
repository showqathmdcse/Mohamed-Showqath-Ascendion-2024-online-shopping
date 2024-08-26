package com.example.shopping.service;

import com.example.shopping.dto.Order;

import java.util.List;

public interface OrderService {
    public Order save(Order order);
    public List<Order> getOrderByCustId(int custId);
}
