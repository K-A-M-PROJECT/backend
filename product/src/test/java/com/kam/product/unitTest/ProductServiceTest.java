package com.kam.product.unitTest;

import com.kam.product.exceptions.ProductNotFoundException;
import com.kam.product.models.Product;
import com.kam.product.repositories.ProductRepository;
import com.kam.product.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;



@SpringBootTest
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1 = new Product(
            null,
            "123",
            "product 1",
            Map.of("color", "red")
    );


    private Product product2 = new Product(
            null,
            "1234",
            "product 2",
            null
    );

    @BeforeEach
     void setUp(){
        this.productRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all products")
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(
               this.product1,
               this.product2
        );
        given(productRepository.findAll()).willReturn(products);

        List<Product> result = productService.getAllProduct();

        assertEquals(2, result.size());
        assertIterableEquals(result, products);

        verify(this.productRepository).findAll();
    }

    @Test
    @DisplayName("Get paginated products")
    public void testGetPaginatedProducts() {
        List<Product> products = Arrays.asList(
                this.product1,
                this.product2
        );
        Page<Product> productPage = new PageImpl<>(products);
        Pageable pageable = PageRequest.of(0, 2);

        given(productRepository.findAll(pageable)).willReturn(productPage);

        List<Product> result = productService.getPaginatedProducts(0, 2).getContent();

        assertEquals(2, result.size());
        assertIterableEquals(result, products);

        verify(this.productRepository).findAll(pageable);
    }

    @Test
    @DisplayName("Get a product by the code: found")
    public void testGetProductByCode() {

        String code = "123";
        given(productRepository.findByCode(code)).willReturn(Optional.of(product1));

        Product result = productService.getProductByCode(code);

        assertEquals(product1, result);
        verify(this.productRepository).findByCode(code);
    }

    @Test
    @DisplayName("Get a product by the code: not found")
    public void testGetProductByCodeNotFound() {
        String code = "123";
        given(productRepository.findByCode(code)).willReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                () -> productService.getProductByCode(code));
        assertEquals("The product with code "+code+" is not found.", exception.getMessage());

    }

    @Test
    @DisplayName("Add Product")
    public void testAddProduct() {
        given(productRepository.save(product1)).willReturn(product1);

        Product result = productService.addProduct(product1);

        assertEquals(this.product1.getName(), result.getName());
        assertEquals(this.product1.getCode(), result.getCode());
        assertEquals(this.product1.getFields(), result.getFields());

        verify(this.productRepository).save(product1);
    }

    @Test
    @DisplayName("Update Product: found")
    public void testUpdateProduct(){
        String code = "123";

        given(this.productRepository.findByCode(code)).willReturn(Optional.of(this.product1));
        given(this.productRepository.save(this.product1)).willReturn(this.product1);

        Product product = this.productService.updateProduct(code, new Product(
                null,
                "159",
                "product 3",
                null
        ));
        assertEquals(this.product1.getName(), product.getName());
        assertEquals(this.product1.getCode(), product.getCode());
        assertEquals(this.product1.getFields(), product.getFields());
    }

    @Test
    @DisplayName("Update Product: not found")
    public void testUpdateProductNotFound(){
        String code = "12356";

        given(this.productRepository.findByCode(code)).willReturn(Optional.empty());
        given(this.productRepository.save(this.product1)).willReturn(this.product1);


        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                ()-> this.productService.updateProduct(code, new Product(
                        null,
                        "159",
                        "product 3",
                        null
                )));
        assertEquals("The product with code "+code+" is not found.", exception.getMessage());

        verify(this.productRepository).findByCode(code);
        verify(this.productRepository, never()).save(this.product1);
    }

    @Test
    @DisplayName("Delete Product")
    public void testDeleteProductByCode() {
        String code = "123";
        willDoNothing().given(this.productRepository).deleteByCode(code);

        this.productService.deleteProductByCode(code);

        verify(this.productRepository).deleteByCode(code);

    }

}