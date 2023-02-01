package com.kam.product.services;

import com.kam.product.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Page<Product> getPaginatedProducts(int page, int size);
    Product getProductByCode(String code);
    boolean addProduct(Product product);
    void deleteProductByCode(String code);
    Product updateProduct(String code, Product updatedProduct);
}
