package com.example.shopping.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int custId;
    private String custName;
    private int custPhone;
    private String custAddress;
    private String custCity;
    private int custPincode;




}
