package com.forceclose.customer.service.service;

import com.forceclose.customer.service.model.Customer;
import com.forceclose.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer register(Customer customer) {
        Customer response = customerRepository.save(customer);
        sendNotification(customer);
        return response;
    }

    @Override
    public Optional<Customer> findById(Long employeeId) {
        return customerRepository.findById(employeeId);
    }

    @Override
    public List<Customer> findByName(String name) {
        return customerRepository.findByFirstName(name);
    }

    private void sendNotification(Customer customer) {
        if (customer== null || StringUtils.isEmpty(customer.getEmail())) return;
        log.debug("Preparing notificacion");

        HttpEntity<Customer> httpEntity = new HttpEntity<>(customer);

        // Send request with POST method.
        String response = restTemplate.postForObject("http://localhost:8080/api/notifications", httpEntity, String.class);

        if (StringUtils.isEmpty(response)) {
            log.info("Notification sent");
        } else {
            log.info("Notification failed");
        }
    }
}
