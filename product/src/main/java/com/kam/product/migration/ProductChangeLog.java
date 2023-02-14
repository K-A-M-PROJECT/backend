package com.kam.product.migration;

import com.kam.product.models.Product;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;

import static java.util.Map.entry;

@ChangeUnit(order = "001", id = "addProducts", author = "admin")
public class ProductChangeLog {

    private final MongoTemplate mongoTemplate;

    public ProductChangeLog(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Execution
    public void addProducts() {
        mongoTemplate.save(new Product("EX01", "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",  Map.ofEntries(
                entry("price", "109.95"),
                entry("description", "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday"),
                entry("category", "men's clothing"),
                entry("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"),
                entry("rating", "3.9")
        )));
        mongoTemplate.save(new Product("EX02", "Patagonia - Black Hole Duffel, 90L", Map.ofEntries(
                entry("price", "149.00"),
                entry("description", "The Black Hole Duffel is made from tough, weather-resistant fabric and features a zippered main compartment and a zippered end pocket for easy access to essentials."),
                entry("category", "men's clothing"),
                entry("image", "https://fakestoreapi.com/img/71APEO0QOL._AC_SL1500_.jpg"),
                entry("rating", "4.8")
        )));

        mongoTemplate.save(new Product("EX03", "Columbia - Silver Ridge Convertible Pants", Map.ofEntries(
                entry("price", "70.00"),
                entry("description", "The Silver Ridge Convertible Pants from Columbia are designed for outdoor activities and feature a zip-off design that converts them into shorts."),
                entry("category", "men's clothing"),
                entry("image", "https://fakestoreapi.com/img/71vHl1QgLRL._AC_SL1500_.jpg"),
                entry("rating", "4.7")
        )));

        mongoTemplate.save(new Product("EX04", "Marmot - PreCip Lightweight Rain Jacket", Map.ofEntries(
                entry("price", "100.00"),
                entry("description", "The Marmot PreCip Lightweight Rain Jacket is perfect for outdoor activities and features Marmot's proprietary NanoPro technology for waterproof and breathable protection."),
                entry("category", "men's clothing"),
                entry("image", "https://fakestoreapi.com/img/71LWZtLl-KL._AC_SL1500_.jpg"),
                entry("rating", "4.5")
        )));

        mongoTemplate.save(new Product("EX05", "The North Face - Hedgehog Hiker Mid Waterproof Hiking Boots", Map.ofEntries(
                entry("price", "120.00"),
                entry("description", "The North Face Hedgehog Hiker Mid Waterproof Hiking Boots are designed for the trails and feature a waterproof membrane and durable construction."),
                entry("category", "men's clothing"),
                entry("image", "https://fakestoreapi.com/img/71fazcwL8WL._AC_SL1500_.jpg"),
                entry("rating", "4.7")
        )));

        mongoTemplate.save(new Product("EX06", "Adidas - Outdoor Terrex Swift R2 GTX Hiking Shoes", Map.ofEntries(
                entry("price", "140.00"),
                entry("description", "The Adidas Outdoor Terrex Swift R2 GTX Hiking Shoes are designed for the trails and feature a waterproof Gore-Tex membrane and a Continental outsole for reliable traction."),
                entry("category", "men's clothing"),
                entry("image", "https://fakestoreapi.com/img/71dztYLlAKL._AC_SL1500_.jpg"),
                entry("rating", "4.4")
        )));

        mongoTemplate.save(new Product("FW01", "Patagonia - Women's Lightweight Synchilla Snap-T Fleece Pullover",  Map.ofEntries(
                entry("price", "119.00"),
                entry("description", "A classic, versatile pullover in a light and soft Synchilla fleece, made of recycled polyester."),
                entry("category", "women's clothing"),
                entry("rating", "4.7")
        )));

        mongoTemplate.save(new Product("FW02", "Lululemon - Wunder Under Hi-Rise Tight 28",  Map.ofEntries(
                entry("price", "98.00"),
                entry("description", "These high-waisted tights are made with sweat-wicking, four-way stretch fabric for a comfortable fit during any activity."),
                entry("category", "women's clothing"),
                entry("rating", "4.8")
        )));

        mongoTemplate.save(new Product("FW03", "Columbia - Women's Bugaboo II Fleece Interchangeable Headwear",  Map.ofEntries(
                entry("price", "29.99"),
                entry("description", "This versatile headwear can be worn as a neck gaiter, hat, or face mask and is made from soft, warm fleece material."),
                entry("category", "women's clothing"),
                entry("rating", "4.6")
        )));

        mongoTemplate.save(new Product("KD01", "The Children's Place - Girls' T-Shirt",  Map.ofEntries(
                entry("price", "7.99"),
                entry("description", "A soft and comfortable T-shirt for girls, available in a variety of colors."),
                entry("category", "kids"),
                entry("rating", "4.3")
        )));

        mongoTemplate.save(new Product("KD02", "Nike - Boys' Dri-FIT Shorts",  Map.ofEntries(
                entry("price", "24.99"),
                entry("description", "Breathable and comfortable shorts for boys, made with Nike's signature Dri-FIT technology."),
                entry("category", "kids"),
                entry("rating", "4.5")
        )));
    }



}