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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderListId;
    private int totalPrice;
    private LocalDate date;
    private String status;
    private int prodQuan;



    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Order order;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "prodId")
    private Product product;

}
