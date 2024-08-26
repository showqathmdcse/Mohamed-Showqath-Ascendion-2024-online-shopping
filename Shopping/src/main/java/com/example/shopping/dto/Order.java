package com.example.shopping.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "Placed")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cust_id")
    @JsonBackReference
    @JsonIgnore
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "orderItemId")
    private List<OrderItem> list=new ArrayList<>();

    private int totalPriceOfOrder;



}
