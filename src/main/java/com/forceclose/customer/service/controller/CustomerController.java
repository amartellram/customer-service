package com.forceclose.customer.service.controller;

import com.forceclose.customer.service.model.Customer;
import com.forceclose.customer.service.exception.BadRequestException;
import com.forceclose.customer.service.exception.ResourceNotFoundException;
import com.forceclose.customer.service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public HttpEntity<List<Customer>> findAll(@RequestParam(value = "name", required = false) String name) {
        List<Customer> customers;
        if(StringUtils.isEmpty(name))
            customers = customerService.findAll();
        else
            customers = customerService.findByName(name);


        if(customers == null || customers.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<Customer> findById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Customer customer = customerService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer register(@RequestBody Customer customer) {
        if(customer.getFirstName() == null) throw new BadRequestException("firstName required!");
        return customerService.register(customer);
    }




}
