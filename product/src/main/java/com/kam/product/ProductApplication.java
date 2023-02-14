package com.kam.product;

import com.mongodb.client.MongoClient;
import io.mongock.driver.api.driver.ConnectionDriver;
import io.mongock.driver.mongodb.springdata.v4.SpringDataMongoV4Driver;
import io.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver;
import io.mongock.runner.springboot.EnableMongock;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockApplicationRunner;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@EnableMongock
@EnableCaching
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public MongockInitializingBeanRunner mongockRunner(ApplicationContext applicationContext, MongoClient mongoClient) {

        MongoSync4Driver driver = MongoSync4Driver.withDefaultLock(mongoClient, "product-db");


        return MongockSpringboot.builder()
                .setDriver(driver)
                .addMigrationScanPackage("com.kam.product.migration")
                .setSpringContext(applicationContext)
                .buildInitializingBeanRunner();
    }
}


