package com.kam.product.services.impl;

import com.kam.product.exceptions.ProductNotFoundException;
import com.kam.product.models.Product;
import com.kam.product.repositories.ProductRepository;
import com.kam.product.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
    }


    @Override
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code).orElseThrow(
                () -> new ProductNotFoundException(code)
        );
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductByCode(String code) {
        productRepository.deleteByCode(code);
    }

    @Override
    public Product updateProduct(String code, Product updatedProduct) {
        Product existingProduct = productRepository.findByCode(code).orElseThrow(
                () -> new ProductNotFoundException(code)
        );
        existingProduct.setCode(updatedProduct.getCode());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setFields(updatedProduct.getFields());
        return productRepository.save(existingProduct);
    }
}
