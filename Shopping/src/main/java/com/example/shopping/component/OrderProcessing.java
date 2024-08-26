package com.example.shopping.component;

import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.Exception.ProductNotFoundException;
import com.example.shopping.Exception.ProductOutOfStockException;
import com.example.shopping.dto.*;
import com.example.shopping.service.CartService;
import com.example.shopping.service.InventryService;
import com.example.shopping.service.OrderService;
import com.example.shopping.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
public class OrderProcessing {

    @PersistenceContext

    @Autowired
    private EntityManager em;

    @Autowired
    ProductService pr;

    @Autowired
    OrderService os;

    @Autowired
    InventryService is;

    @Autowired
    CartService cs;




    public String placeOrder(Order order,int custId) throws CustomerNotFoundException, ProductOutOfStockException {
        Cart cart =em.find(Cart.class, custId);
        List<CartProduct> list = cart.getCp();
        boolean successful=false;
        Inventry inv;
        List<Inventry> invList=new ArrayList<>();
        for (CartProduct cartProduct : list) {
            successful=false;
              inv = is.findByProduct(cartProduct.getProduct());
            if (inv.getProQuan() >= cartProduct.getQuant())
            {
                successful=true;
            }
            if(successful){
                inv.setProQuan(inv.getProQuan()-cartProduct.getQuant());
               invList.add(inv);

            }
            else{
                successful=false;
                throw new ProductOutOfStockException("Product is out of stock");

            }
        }
        if(successful){
            os.save(order);
            cs.delete(custId);
            for(Inventry inventry:invList){
                is.upd(inventry);
            }
            return "order successfully placed";
        }


        return "order failed";

    }

   public String placeOrderCheckout(int prodId,int quan,Order order) throws ProductNotFoundException, ProductOutOfStockException {
        boolean successful=false;

        Inventry inv=is.findByProduct(pr.findById(prodId));

        if(inv==null)
        {
            throw new ProductNotFoundException("Product with ID " + prodId + " not found");
        }

        if(inv.getProQuan()>=quan){
            os.save(order);
            successful=true;
        }
        if(successful){
            inv.setProQuan(inv.getProQuan()-quan);
            is.upd(inv);
            return "order placed successfully";
        }

        throw new ProductOutOfStockException("Product is out of stock");

   }

   @Transactional
   public String cancelOrder(int custId,int orderId){
        Order order=em.find(Order.class,orderId);
        List<OrderItem> olist=new ArrayList<>(order.getList());
        Inventry inv;
        List<Inventry> invlist=new ArrayList<>();
        for(OrderItem ol:olist){
            if(ol.getStatus().equals("ORDER CANCELED")){
                return "Your order is already canceled";
            }
            ol.setStatus("ORDER CANCELED");
            inv=is.findByProduct(ol.getProduct());
            inv.setProQuan(inv.getProQuan()+ol.getProdQuan());
            invlist.add(inv);
            order.getList().add(ol);
            ol.setOrder(order);
        }
       for(Inventry inventry:invlist){
           is.upd(inventry);
       }


    return "order canceled";

   }

}





