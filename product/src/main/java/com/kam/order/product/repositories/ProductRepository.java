package com.kam.order.product.repositories;

import com.kam.order.product.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(String code);
    void deleteByCode(String code);
}
