package com.example.shopping.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Inventry {
    @Id
    private int invId;
    private String invName;
    private String dealerName;
    private int proQuan ;
    private String stockStaus;


    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "prodid")
    private Product product;




}
