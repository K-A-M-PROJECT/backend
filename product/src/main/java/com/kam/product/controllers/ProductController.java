package com.kam.product.controllers;

import com.kam.product.models.Product;
import com.kam.product.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
@Component
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok().body(products);
    }
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable("page") int page,
                                 @PathVariable("size") int size) {
        List<Product> products = productService.getPaginatedProducts(page, size);
        return ResponseEntity.ok().body(products);
    }
    @GetMapping("/{code}")
    public ResponseEntity<Product> getProduct(@PathVariable("code") String code) {
        Product product = productService.getProductByCode(code);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return ResponseEntity.ok().body(addedProduct);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("code") String code) {
        productService.deleteProductByCode(code);
        // returns a 204 No Content HTTP response, indicating that the resource was successfully deleted.
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Product> updateProduct(@PathVariable("code") String code,
                          @RequestBody Product updateProduct) {
        Product updatedProduct = productService.updateProduct(code, updateProduct);
        return ResponseEntity.ok().body(updatedProduct);
    }
}
