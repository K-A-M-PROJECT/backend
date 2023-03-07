package com.kam.product.services;

import com.kam.product.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Page<Product> getPaginatedProducts(Integer page, Integer size);
    Product getProductByCode(String code);
    Product addProduct(Product product);
    void deleteProductByCode(String code);
    Product updateProduct(String code, Product updatedProduct);
}
