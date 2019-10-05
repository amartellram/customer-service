package com.forceclose.customer.service.controller;


import com.forceclose.customer.service.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@Slf4j
public class NotificationController {

    @PostMapping
    public String sendNotification(@RequestBody Customer customer) {
        log.info("Notification Sent");
        return "Success";
    }
}
