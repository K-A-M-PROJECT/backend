package com.kam.product.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id = UUID.randomUUID().toString();

    private String code;
    private String name;
    private Map<String, Object> fields;



}
