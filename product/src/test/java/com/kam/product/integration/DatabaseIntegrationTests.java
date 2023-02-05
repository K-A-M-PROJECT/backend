package com.kam.product.integration;

import com.kam.product.controllers.ProductController;
import com.kam.product.exceptions.ProductNotFoundException;
import com.kam.product.models.Product;
import com.kam.product.repositories.ProductRepository;
import com.kam.product.services.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class DatabaseIntegrationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductController productController;

    private List<Product> products;

    @Before
    void init(){
        this.productRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
         Product product1 = new Product(
                "123",
                "product 1",
                Map.of("color", "red")
        );

         Product product2 = new Product(
                "1234",
                "product 2",
                null
        );

        Product product3 = new Product(
                "12345",
                "product 2",
                Map.of("color", "green")
        );

        products = Arrays.asList(product1, product2, product3);
        this.productRepository.saveAll(products);
    }

    @AfterEach
    void tearDown() {
        this.productRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all products")
    public void testGetAllProducts(){
        List<Product> products = this.productController.getAllProducts().getBody();
        assertThat(products.size()).isEqualTo(3);
        assertThat(products).isEqualTo(this.products);
    }

    @Test
    @DisplayName("Get paginated products")
    public void testGetPaginatedProducts(){
        List<Product> products = this.productController.getAllProducts(0, 3).getBody();
        assertThat(products.size()).isEqualTo(3);
        assertThat(products).isEqualTo(this.products);
    }

    @Test
    @DisplayName("Get a product by the code: found")
    public void testGetProductByCode(){
        Product product = this.productController.getProduct("12345").getBody();
        assertThat(product).isEqualTo(this.products.get(2));
    }

    @Test
    @DisplayName("Get a product by the code: not found")
    public void testGetProductByCodeNotFound() {
        Exception exception = assertThrows(ProductNotFoundException.class, ()->
                        this.productController.getProduct("123456")
                );
        assertEquals("The product with code 123456 is not found.", exception.getMessage());
    }

    @Test
    @DisplayName("Add product")
    void addProduct_ShouldReturnProduct(){
        Product product = this.productController.addProduct(this.products.get(0)).getBody();
        assertThat(product).isEqualTo(this.products.get(0));
    }

    @Test
    @DisplayName("Delete Product")
    void deleteProduct_ShouldDeleteProduct(){

        ResponseEntity<Void> response = this.productController.deleteProduct("123");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    @DisplayName("Update an existing product")
    void updateProduct_ShouldUpdateProduct(){
        Product product = new Product("123", "Car", Map.of("Price", 400000));
        Product updatedProduct = this.productController.updateProduct("123", product).getBody();
        product.setId(updatedProduct.getId());

        assertThat(product).isEqualTo(updatedProduct);

    }


    @Test
    @DisplayName("Update a non existing product")
    void updateProduct_ShouldUpdateProductNotFound(){
        Exception exception = assertThrows(ProductNotFoundException.class, ()->
                this.productController.updateProduct("123456", new Product())
        );
        assertEquals("The product with code 123456 is not found.", exception.getMessage());
    }



}
