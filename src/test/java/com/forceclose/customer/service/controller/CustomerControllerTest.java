package com.forceclose.customer.service.controller;

import com.forceclose.customer.service.model.Customer;
import com.forceclose.customer.service.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean() {
        reset(customerService);
    }

    @Test
    @DisplayName("Test Consulta Empleado - Ok")
    void getAll_ok() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("jperez@gmail.com");
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        List<Customer> customers = List.of(customer);

        when(customerService.findAll()).thenReturn(customers);

        this.mockMvc.perform(get("/api/customers")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Juan"))
                .andExpect(jsonPath("$[0].lastName").value("Perez"))
                .andExpect(jsonPath("$[0].email").value("jperez@gmail.com"));
    }

    @Test
    @DisplayName("Test Consulta Empleado - Lista Vacía")
    void getAll_empty() throws Exception {

    }

    @Test
    void register() {

    }
}