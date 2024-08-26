package com.example.shopping.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleProductNotFound(ProductNotFoundException e) {
        return new ErrorResponse(500,e.toString());

    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String>invalidInputHandler(MethodArgumentNotValidException e){
        Map<String,String> emap = new HashMap<>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach((error)->{
                    emap.put(error.getField(),error.getDefaultMessage());
                });
        return emap;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handleProductNotFound(CustomerNotFoundException e) {
        return new ErrorResponse(500,e.toString());

    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProductOutOfStockException.class)
    public ErrorResponse handleProductNotFound(ProductOutOfStockException e) {
        return new ErrorResponse(500,e.toString());

    }

}
