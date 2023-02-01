package com.kam.product.repositories;

import com.kam.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(String code);
    void deleteByCode(String code);
    Page<Product> findAll(Pageable pageable);
}
