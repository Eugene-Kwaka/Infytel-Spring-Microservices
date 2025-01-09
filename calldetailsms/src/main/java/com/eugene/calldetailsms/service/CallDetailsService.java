package com.eugene.calldetailsms.service;

import com.eugene.calldetailsms.dto.CallDetailsDTO;
import com.eugene.calldetailsms.entities.CallDetails;
import com.eugene.calldetailsms.repository.CallDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CallDetailsRepository callDetailsRepository;

    public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable Long phoneNo){

        logger.info("Call details request for customer {}", phoneNo);

        List<CallDetails> callDetails = callDetailsRepository.findByCalledBy(phoneNo);

        List<CallDetailsDTO> callsDTO = new ArrayList<>();

        for (CallDetails call : callDetails){
            callsDTO.add(CallDetailsDTO.valueOf(call));
        }

        return callsDTO;
    }


}
