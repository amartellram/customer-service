package com.forceclose.customer.service.service;

import com.forceclose.customer.service.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Customer register(Customer customer);

    Optional<Customer> findById(Long employeeId);

    List<Customer> findByName(String name);
}
