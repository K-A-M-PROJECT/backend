package com.kam.order.service;


import com.kam.order.models.order.Order;
import com.kam.order.models.user.Customer;
import com.kam.order.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    public void deleteById(long customerId) {
        Customer customer =  this.customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        this.customerRepository.deleteById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer getCustomerById(long customerId) {
        return this.customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }


}
