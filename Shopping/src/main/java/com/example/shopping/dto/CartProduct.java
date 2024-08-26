package com.example.shopping.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cpId;
    private int price;
    private int totalPrice;
    private int quant;
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cartid")
    @JsonIgnore
    private Cart cart;

}
