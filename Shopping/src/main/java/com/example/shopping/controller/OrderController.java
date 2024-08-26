package com.example.shopping.controller;

import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.Exception.ProductNotFoundException;
import com.example.shopping.Exception.ProductOutOfStockException;
import com.example.shopping.component.OrderProcessing;
import com.example.shopping.dto.Customer;
import com.example.shopping.dto.Order;
import com.example.shopping.dto.OrderItem;
import com.example.shopping.dto.Product;
import com.example.shopping.repo.CustomerRepo;
import com.example.shopping.service.CustomerService;
import com.example.shopping.service.OrderService;
import com.example.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shop/order")
public class OrderController {

    @Autowired
    CustomerService cs;

    @Autowired
    ProductService ps;

    @Autowired
    OrderProcessing op;

    @Autowired
    OrderService os;

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam("custId") int custId,@RequestParam("prodId") int prodId,@RequestParam("quan") int quan) throws ProductNotFoundException, CustomerNotFoundException, ProductOutOfStockException {
      Customer customer=cs.findById(custId);
      Order order=new Order();
      order.setCustomer(customer);
      order.setList(new ArrayList<>());

      Product product= ps.findById(prodId);

      if(product==null){
          return "product not found";
      }

        OrderItem orderei=new OrderItem();
       orderei.setProduct(product);
       orderei.setDate(LocalDate.now());
       orderei.setStatus("ordered");
       orderei.setTotalPrice(product.getProdPrice()*quan);
       orderei.setProdQuan(quan);
       order.getList().add(orderei);
       order.setTotalPriceOfOrder(order.getTotalPriceOfOrder()+ orderei.getTotalPrice());
       orderei.setOrder(order);

       return op.placeOrderCheckout(prodId,quan,order);

    }

    @GetMapping("/vieworder")
    public List<Order> viewOreder(@RequestParam("custId")int custId)
    {
      List<Order>orderList= os.getOrderByCustId(custId);
        return orderList;
    }

    @PutMapping("/cancelorder")
    public String cancel(@RequestParam("custId")int custId,@RequestParam("orderId")int orderId){
       return op.cancelOrder(custId, orderId);
    }


}
