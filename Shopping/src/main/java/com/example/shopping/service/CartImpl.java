package com.example.shopping.service;

import com.example.shopping.Exception.CustomerNotFoundException;
import com.example.shopping.dto.Cart;
import com.example.shopping.dto.CartProduct;
import com.example.shopping.repo.CartRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartImpl implements CartService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    CartRepo cre;



    @Override
    public Cart add(Cart c) {
        return cre.save(c);
    }

    @Override
    public Cart update(Cart c) {
        return cre.save(c);
    }

    @Override
    public String delete(int custId) throws CustomerNotFoundException {
        Cart cart = findByCustomer(custId);
        if (cart != null) {
            List<CartProduct> cp = cart.getCp();
            boolean exist = true;
            for (CartProduct ce : cp) {
                if (ce.getProduct() == null) {
                    exist = false;
                    break;

                }
            }
            if (exist) {
                int id=cart.getCartId();
                String sql="DELETE FROM cart_product WHERE cartid="+id+" ";
                Query q=em.createNativeQuery(sql);
                q.executeUpdate();
                return "your cart is empty now";
            }
        }
        return "your cart is already empty";
    }

    @Override
    public List<Cart> display() {
        return cre.findAll();
    }

    @Override
    @Transactional
    public Cart findByCustomer(int custId) throws CustomerNotFoundException {
        Cart cart=em.find(Cart.class, custId);
        if(cart==null) {
            throw new CustomerNotFoundException("cart and customer is not founded for this customer_id");
        }
        return cart;
    }


}
