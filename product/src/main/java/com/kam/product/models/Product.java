package com.kam.product.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    private String code;
    private String name;
    private Map<String, Object> fields;

    public Product(String code, String name, Map<String, Object> fields) {
        this.code = code;
        this.name = name;
        this.fields = fields;
    }
}
