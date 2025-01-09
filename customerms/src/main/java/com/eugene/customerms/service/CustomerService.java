package com.eugene.customerms.service;

import com.eugene.customerms.dto.CustomerDTO;
import com.eugene.customerms.dto.LoginDTO;
import com.eugene.customerms.dto.PlanDTO;
import com.eugene.customerms.entity.Customer;
import com.eugene.customerms.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public final ModelMapper modelMapper = new ModelMapper();

    public RestTemplate restTemplate = new RestTemplate();

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

    public CustomerDTO getCustomerProfile(Long phoneNo) {
        logger.info("Profile request for customer {}", phoneNo);

        Customer customer = customerRepository.findByPhoneNo(phoneNo);

        CustomerDTO customerDTO = CustomerDTO.valueOf(customer);


        // Using RestTemplate to make a call to the Plan service to get the plan details based on the PlanId
        PlanDTO planDTO = restTemplate.getForObject("http://localhost:8400/plans/"+customerDTO.getCurrentPlan().getPlanId(), PlanDTO.class);
        customerDTO.setCurrentPlan(planDTO);

        // Accessing FriendFamily microservice using RestTemplate
        List<Long> friends = restTemplate.getForObject("http://localhost:8300/customers/"+phoneNo+"/friends", List.class);
        customerDTO.setFriendAndFamily(friends);

        return customerDTO;



    }
}

