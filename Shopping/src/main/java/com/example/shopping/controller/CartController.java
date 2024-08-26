package com.example.shopping.controller;


import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.Exception.ProductNotFoundException;
import com.example.shopping.Exception.ProductOutOfStockException;
import com.example.shopping.component.OrderProcessing;
import com.example.shopping.dto.*;
//import com.example.shopping.repo.Sample;
import com.example.shopping.service.CartService;
import com.example.shopping.service.CustomerService;
import com.example.shopping.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shop/cart")
public class CartController {

    @PersistenceContext

    @Autowired
    private EntityManager em;


    @Autowired
    CustomerService customerService;

    @Autowired
    CartService cs;


    @Autowired
    ProductService ps;


    @Autowired
    OrderProcessing cm;

    @PostMapping("/addtocart")
    public void add(@RequestParam("custId") int id, @RequestParam("prodId") int prodId) throws ProductNotFoundException, CustomerNotFoundException {
        Cart cart = cs.findByCustomer(id);
        Product p1 = ps.findById(prodId);
        List<CartProduct> cp = cart.getCp();
        boolean exist = false;
        int cpi = 0;
        for (CartProduct ce : cp) {
            if (ce.getProduct() == p1) {
                cpi = ce.getCpId();
                exist = true;
                break;
            }
        }
        if (exist) {
            for (CartProduct cd : cp) {
                if (cd.getCpId() == cpi) {
                    cd.setTotalPrice(cd.getTotalPrice() + cd.getPrice());
                    cd.setQuant(cd.getQuant() + 1);
                    cd.setDate(cd.getDate());
                }
            }
        } else {
            CartProduct cd = new CartProduct();
            cd.setProduct(p1);
            cd.setQuant(1);
            cd.setTotalPrice(p1.getProdPrice());
            cd.setPrice(p1.getProdPrice());
            cd.setDate(p1.getDeliveryDate());
            cart.getCp().add(cd);
        }
        cs.add(cart);
    }

    @PutMapping("/update")
    public String update(@RequestParam("custId") int id, @RequestParam("prodId") int prodId) throws ProductNotFoundException, CustomerNotFoundException {
        Cart cart = cs.findByCustomer(id);
        Product p1 = ps.findById(prodId);
        List<CartProduct> cp = cart.getCp();
        boolean exist = false;
        int cpi = 0;
        for (CartProduct ce : cp) {
            if (ce.getProduct() == p1) {
                cpi = ce.getCpId();
                exist = true;
                break;
            }
        }
        if (exist) {
            for (CartProduct cd : cp) {
                if (cd.getCpId() == cpi) {

                    if (cd.getQuant() >0) {
                        cd.setTotalPrice(cd.getTotalPrice() - cd.getPrice());
                        cd.setQuant(cd.getQuant() - 1);
                    } else  {
                        String sql = "DELETE FROM cart_product where quant=0";
                        Query q = em.createNativeQuery(sql);
                        q.executeUpdate();
                    }
                }
            }


        }
         cs.update(cart);
        return "successfully updated";
    }


    @DeleteMapping("/deleteallproducts")
    @Transactional
    public String delete(@RequestParam("custId")int id) throws CustomerNotFoundException {
        return cs.delete(id);
    }

    @GetMapping("/viewcartbyId")
    public Cart viewcart(@RequestParam("custId")int id) throws CustomerNotFoundException {
        return cs.findByCustomer(id);
    }

    @GetMapping("/displayallcart")
    public List<Cart> displayallcart(){
        return cs.display();
    }

    @PostMapping("/placeorder")
    @Transactional
    public String addOrder(@RequestParam("custId") int custId) throws CustomerNotFoundException, ProductOutOfStockException {
        Cart cart=cs.findByCustomer(custId);
        Order order=new Order();
        order.setCustomer(cart.getCustomer());
        OrderItem orderItem;
        List<CartProduct> list=cart.getCp();
        for(CartProduct cartProduct:list){
            orderItem=new OrderItem();
            orderItem.setStatus("ordered");
            orderItem.setDate(LocalDate.now());
            orderItem.setTotalPrice(cartProduct.getTotalPrice());
            orderItem.setProdQuan(cartProduct.getQuant());
            orderItem.setProduct(cartProduct.getProduct());
            order.getList().add(orderItem);
            order.setTotalPriceOfOrder(order.getTotalPriceOfOrder()+ orderItem.getTotalPrice());
            orderItem.setOrder(order);
        }

        return cm.placeOrder(order,custId);

    }





}
