package com.kam.order.product.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private String code;

    public ProductNotFoundException(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return String.format("The product with code %s is not found.", getCode());
    }

    public String getCode() {
        return code;
    }
}
