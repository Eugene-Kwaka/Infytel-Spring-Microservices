package com.eugene.customerms.service;

import com.eugene.customerms.dto.CustomerDTO;
import com.eugene.customerms.dto.LoginDTO;
import com.eugene.customerms.dto.PlanDTO;
import com.eugene.customerms.entity.Customer;
import com.eugene.customerms.repository.CustomerRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerCircuitBreakerService customerCircuitBreakerService;


    @Autowired
    CustomerRepository customerRepository;

    public void createCustomer(CustomerDTO customerDTO) {
        logger.info("Creation request for customer {}", customerDTO);

        // Converting the customerDTO passed by user to an entity that will be saved in the Database
        Customer customer = customerDTO.createEntity();
        customerRepository.save(customer);
    }

    public boolean login(LoginDTO loginDTO) {
        logger.info("Login request for customer {} with password {}", loginDTO.getPhoneNo(), loginDTO.getPassword());

        Customer customer = customerRepository.findByPhoneNo(loginDTO.getPhoneNo());
        return customer != null && customer.getPassword().equals(loginDTO.getPassword());
    }

    @CircuitBreaker(name="customerService", fallbackMethod = "getCustomerProfileFallback")
    public CustomerDTO getCustomerProfile(Long phoneNo) {
        logger.info("Profile request for customer {}", phoneNo);

        Customer customer = customerRepository.findByPhoneNo(phoneNo);

        CustomerDTO customerDTO = CustomerDTO.valueOf(customer);

        //Future<PlanDTO> planDTOFuture = customerCircuitBreakerService.getPlan(customerDTO.getCurrentPlan().getPlanId());

        CompletableFuture<PlanDTO> planFuture = customerCircuitBreakerService.getPlan(customerDTO.getCurrentPlan().getPlanId());

        //Future<List<Long>> friendsFuture = customerCircuitBreakerService.getCustomerProfile(phoneNo);

        CompletableFuture<List<Long>> friendsFuture = customerCircuitBreakerService.getSpecificFriends(phoneNo);

        // Combine results
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(planFuture, friendsFuture);

        logger.info("Fetched plan: {}", planFuture.join());
        logger.info("Fetched friends: {}", friendsFuture.join());


        // Wait for all async tasks to complete
        allFutures.join();

        // Set results into CustomerDTO
        customerDTO.setCurrentPlan(planFuture.join());
        customerDTO.setFriendAndFamily(friendsFuture.join());

        //customerDTO.setFriendAndFamily(friendsFuture.get());

        //customerDTO.setCurrentPlan(planDTOFuture.get());

        return customerDTO;

    }

    public CustomerDTO getCustomerProfileFallback(Long phoneNo, Throwable throwable){
        logger.info("======In Fallback=====");
        logger.warn("Fallback triggered for phoneNo {}. Reason: {}", phoneNo, throwable.toString(), throwable);

        CustomerDTO fallbackDto = new CustomerDTO();
        fallbackDto.setPhoneNo(phoneNo);
        fallbackDto.setName("Unavailable");
        fallbackDto.setFriendAndFamily(Collections.emptyList());

        return fallbackDto;
    }
}

