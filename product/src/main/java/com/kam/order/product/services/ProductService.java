package com.kam.order.product.services;

import com.kam.order.product.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    List<Product> getPaginatedProducts(int page, int size);
    Product getProductByCode(String code);
    Product addProduct(Product product);
    void deleteProductByCode(String code);
    Product updateProduct(String code, Product updatedProduct);
}
