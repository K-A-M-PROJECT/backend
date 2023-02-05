package com.kam.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kam.product.models.Product;
import com.kam.product.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;



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

    @Test
    @DisplayName("Get all products")
    public void testGetAllProducts() throws Exception {
        List<Product> products = List.of(
                this.product1,
                this.product2
        );
        given(productService.getAllProduct()).willReturn(products);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].code", is(this.product1.getCode())))
                .andExpect(jsonPath("$[0].name", is(this.product1.getName())))
                .andExpect(jsonPath("$[0].fields", is(this.product1.getFields())))

                .andExpect(jsonPath("$[1].code", is(this.product2.getCode())))
                .andExpect(jsonPath("$[1].name", is(this.product2.getName())))
                .andExpect(jsonPath("$[1].fields", is(this.product2.getFields())));

        verify(this.productService).getAllProduct();
    }

    @Test
    @DisplayName("Get paginated products")
    public void testGetPaginatedProducts() throws Exception {
        List<Product> products = List.of(
                this.product1,
                this.product2
        );
        given(productService.getPaginatedProducts(1, 2)).willReturn(products);

        mockMvc.perform(get("/product/1/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].code", is(this.product1.getCode())))
                .andExpect(jsonPath("$[0].name", is(this.product1.getName())))
                .andExpect(jsonPath("$[0].fields", is(this.product1.getFields())))

                .andExpect(jsonPath("$[1].code", is(this.product2.getCode())))
                .andExpect(jsonPath("$[1].name", is(this.product2.getName())))
                .andExpect(jsonPath("$[1].fields", is(this.product2.getFields())));

        verify(this.productService).getPaginatedProducts(1, 2);
    }

    @Test
    @DisplayName("Get a product by the code: found")
    public void testGetProductByCode() throws Exception {
        String code = "123";
        given(productService.getProductByCode(code)).willReturn(product1);

        mockMvc.perform(get("/product/{code}", code))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(this.product1.getCode())))
                .andExpect(jsonPath("$.name", is(this.product1.getName())))
                .andExpect(jsonPath("$.fields", is(this.product1.getFields())));

        verify(this.productService).getProductByCode(code);
    }

    @Test
    @DisplayName("Add product")
    void addProduct_ShouldReturnProduct() throws Exception {
        given(productService.addProduct(product1)).willReturn(product1);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(this.product1.getCode())))
                .andExpect(jsonPath("$.name", is(this.product1.getName())))
                .andExpect(jsonPath("$.fields", is(this.product1.getFields())));

        verify(this.productService).addProduct(this.product1);
    }

    @Test
    @DisplayName("Delete Product")
    void deleteProduct_ShouldDeleteProduct() throws Exception {
        String code = "123";
        willDoNothing().given(this.productService).deleteProductByCode(code);

        mockMvc.perform(delete("/product/{code}", code))
                .andExpect(status().isOk());

        verify(this.productService).deleteProductByCode(code);
    }

    @Test
    @DisplayName("Update an existing product")
    void updateProduct_ShouldUpdateProduct() throws Exception {
        String code = "123";
        given(this.productService.getProductByCode(code)).willReturn(product1);


        given(productService.updateProduct(code, this.product2)).willReturn(this.product2);

        mockMvc.perform(put("/product/{code}", code)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(product2.getCode())))
                .andExpect(jsonPath("$.name", is(product2.getName())))
                .andExpect(jsonPath("$.fields", is(product2.getFields())));

        verify(this.productService).updateProduct(code, product2);
    }

}
