package com.eugene.calldetailsms.controller;

import com.eugene.calldetailsms.dto.CallDetailsDTO;
import com.eugene.calldetailsms.service.CallDetailsService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("customers/")
public class CallDetailsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CallDetailsService callDetailsService;

    @GetMapping("/{phoneNo}/calldetails")
    public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable Long phoneNo){
        logger.info("Call details request for customer {}", phoneNo);

        return callDetailsService.getCustomerCallDetails(phoneNo);
    }
}
