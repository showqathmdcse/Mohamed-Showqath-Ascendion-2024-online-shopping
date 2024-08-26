package com.example.shopping.repo;

import com.example.shopping.dto.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<CartProduct,Integer> {
}
