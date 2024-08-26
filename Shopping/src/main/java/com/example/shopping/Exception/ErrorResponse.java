package com.example.shopping.Exception;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter@ToString@EqualsAndHashCode
public class ErrorResponse {
    private int eCode;
    private String eMsg;
}
