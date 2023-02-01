package com.kam.product.services.impl;

import com.kam.product.models.Product;
import com.kam.product.repositories.ProductRepository;
import com.kam.product.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }


    @Override
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("Product not found!")
        );
    }

    @Override
    public boolean addProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public void deleteProductByCode(String code) {
        productRepository.deleteByCode(code);
    }

    @Override
    public Product updateProduct(String code, Product updatedProduct) {
        Product existingProduct = productRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("Product not found!")
        );
        existingProduct.setCode(updatedProduct.getCode());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setFields(updatedProduct.getFields());
        return productRepository.save(existingProduct);
    }
}
