package com.kam.order.repository;

import com.kam.order.models.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerById(long customerId);
    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerByUserName(String username);
    void deleteById(long customerId);
}
