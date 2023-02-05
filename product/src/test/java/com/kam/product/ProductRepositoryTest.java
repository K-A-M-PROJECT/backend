package com.kam.product;

import com.kam.product.models.Product;
import com.kam.product.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.assertions.Assertions.assertTrue;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product1 = new Product(
            null,
            "123",
            "product 1",
            Map.of("color", "red")
    );


    @BeforeEach
    void setUp(){
        this.productRepository.deleteAll();
    }

    @Test
    @DisplayName("Check if the product exists by code")
    public void shouldCheckIfProductExistCode(){
        String code = "123";

        this.productRepository.save(product1);

        Optional<Product> expected = this.productRepository.findByCode(code);
        assertTrue(expected.isPresent());
    }

    @Test
    @DisplayName("Check if the product does not exist by code")
    public void shouldCheckIfProductCodeDoesNotExist(){

        this.productRepository.save(product1);

        Optional<Product> expected = this.productRepository.findByCode("1236");
        assertFalse(expected.isPresent());
    }

    @Test
    @DisplayName("Check if the product has been deleted")
    public void deleteByCode(){
        String code = "123";
        this.productRepository.save(product1);

        this.productRepository.deleteByCode(code);

        Optional<Product> expected = this.productRepository.findByCode(code);
        assertFalse(expected.isPresent());
    }


}
