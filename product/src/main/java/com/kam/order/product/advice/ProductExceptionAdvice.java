package com.kam.order.product.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kam.order.product.exceptions.ProductNotFoundException;

@ControllerAdvice
@RestController
public class ProductExceptionAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String documentNotFoundHandler(ProductNotFoundException ex) {
        return ex.getMessage();
    }
}
