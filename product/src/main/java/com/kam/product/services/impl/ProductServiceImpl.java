package com.kam.product.services.impl;

import com.kam.product.exceptions.ProductNotFoundException;
import com.kam.product.models.Product;
import com.kam.product.repositories.ProductRepository;
import com.kam.product.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, List<Product>> redisTemplate;


    @Cacheable(value = "products", key = "#root.methodName")
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getPaginatedProducts(int page, int size) {
        String cacheKey = "products-" + page + "-" + size;
        List<Product> cachedProducts = redisTemplate.opsForValue().get(cacheKey);

        if (cachedProducts != null) {
            return cachedProducts;
        }

        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findAll(pageable).getContent();

        redisTemplate.opsForValue().set(cacheKey, products);

        return products;
    }


    @Override
    @Cacheable(value = "productByCode", key = "#code")
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code).orElseThrow(
                () -> new ProductNotFoundException(code)
        );
    }

    @Override
    @CacheEvict(value = {"products", "paginatedProducts", "productByCode"}, key = "#root.methodName", allEntries = true)
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @CacheEvict(value = {"products", "paginatedProducts", "productByCode"}, key = "#root.methodName", allEntries = true)
    public void deleteProductByCode(String code) {
        productRepository.deleteByCode(code);
    }

    @Override
    @CacheEvict(value = {"products", "paginatedProducts", "productByCode"}, key = "#root.methodName", allEntries = true)
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
