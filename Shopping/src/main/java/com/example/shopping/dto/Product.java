package com.example.shopping.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {
    @Id
    private int prodId;
    private String prodName;
    private int prodPrice;
    private String prodCategory;
    private String prodDes;
    private LocalDate deliveryDate;

}
