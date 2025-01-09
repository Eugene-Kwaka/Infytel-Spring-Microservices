package com.eugene.customerms.controller;

import com.eugene.customerms.dto.CustomerDTO;
import com.eugene.customerms.dto.LoginDTO;
import com.eugene.customerms.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerService customerService;

    // Create a new customer
    @PostMapping("/customers")
    public void createCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.info("Creation request for customer {}", customerDTO);
        customerService.createCustomer(customerDTO);
    }

    // Login
    @PostMapping("/login")
    public boolean login(@RequestBody LoginDTO loginDTO) {
        logger.info("Login request for customer {} with password {}", loginDTO.getPhoneNo(), loginDTO.getPassword());
        return customerService.login(loginDTO);
    }

    // Fetch full profile of a specific customer
    @GetMapping("/customers/{phoneNo}")
    public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) {
        logger.info("Profile request for customer {}", phoneNo);
        return customerService.getCustomerProfile(phoneNo);
    }
}

