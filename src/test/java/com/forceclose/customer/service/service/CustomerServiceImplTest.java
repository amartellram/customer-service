package com.forceclose.customer.service.service;

import com.forceclose.customer.service.model.Customer;
import com.forceclose.customer.service.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private  RestTemplate restTemplate;

    private CustomerService customerService;

    @BeforeEach
    void init() {
        this.customerService = new CustomerServiceImpl(customerRepository, restTemplate);
    }

    @Test
    void findAll() {
        Customer customer = new Customer();
        customer.setEmail("jperez@gmail.com");
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        List<Customer> customers = List.of(customer);

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> response = customerService.findAll();

        assertThat(response).hasSize(1);
        assertThat(response.get(0))
                .hasFieldOrPropertyWithValue("firstName", "Juan")
                .hasFieldOrPropertyWithValue("lastName", "Perez")
                .hasFieldOrPropertyWithValue("email", "jperez@gmail.com");
    }

    @Test
    void register() {
    }
}