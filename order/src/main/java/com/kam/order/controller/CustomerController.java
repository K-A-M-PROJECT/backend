package com.kam.order.controller;

import com.kam.order.models.user.Customer;
import com.kam.order.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;


    @Operation(summary = "To get all customers from DB")
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }

    @Operation(summary = "To get a customer from DB by id")
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }


    @Operation(summary = "To add a customer to DB, you will add it without id key of JSON or set Id = 0.")
    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        this.customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "To update a customer in DB, you will add it with id key of JSON")
    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        this.customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "To delete a customer from DB by id")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id) {
        this.customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
