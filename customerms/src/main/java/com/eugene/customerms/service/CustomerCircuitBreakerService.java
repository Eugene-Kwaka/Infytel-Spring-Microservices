package com.eugene.customerms.service;

import com.eugene.customerms.client.FriendFamilyFeignClient;
import com.eugene.customerms.client.PlanFeignClient;
import com.eugene.customerms.dto.PlanDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import io.vavr.concurrent.Future;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerCircuitBreakerService {

    @Autowired
    PlanFeignClient planFeignClient;

    @Autowired
    FriendFamilyFeignClient friendFamilyFeignClient;

//    @CircuitBreaker(name="customerService")
//    public Future<PlanDTO> getPlan(Integer planId){
//
//        return Future.of(() -> planFeignClient.getPlan(planId));
//    }

    @CircuitBreaker(name="customerService")
    public CompletableFuture<PlanDTO> getPlan(Integer planId){

        return CompletableFuture.supplyAsync(() -> planFeignClient.getPlan(planId));
    }
//    @CircuitBreaker(name="customerService")
//    public Future<List<Long>> getCustomerProfile(Long phoneNo){
//
//        return Future.of(() -> friendFamilyFeignClient.getCustomerProfile(phoneNo));
//    }

    @CircuitBreaker(name="customerService")
    public CompletableFuture<List<Long>> getSpecificFriends(Long phoneNo){

        return CompletableFuture.supplyAsync(() -> friendFamilyFeignClient.getSpecificFriends(phoneNo));
    }

}
