package com.kam.product.services;

import com.kam.product.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    List<Product> getPaginatedProducts(String page, int size);
    Product getProductByCode(String code);
    Product addProduct(Product product);
    void deleteProductByCode(String code);
    Product updateProduct(String code, Product updatedProduct);
}
