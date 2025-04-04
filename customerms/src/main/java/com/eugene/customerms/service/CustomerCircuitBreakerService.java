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

    /**
     * The @CircuitBreaker annotation doesn't work properly inside CompletableFuture.supplyAsync()
     * In the previous case, it was applied to the outer method (getPlan(), getSpecificFriends()) not what runs inside .supplyAsync() method itself.
     * So Resilience4j wraps the method returning CompletableFuture, but it does not track what happens in the supplyAsync(), because that part runs in a different thread, outside Resilience4j's context.
     * This results in:
     *  1. IF the Feign call fails or times out inside the async block, Resilience4j won't know about it.
     *  2. The circuit breaker might never open or recover.
     *  3. Failures don't trigger the callback function properly.
     *  4. Logging and metrics are broken/incomplete.
     *  */

//    @CircuitBreaker(name="customerService")
    public CompletableFuture<PlanDTO> getPlan(Integer planId){

        return CompletableFuture.supplyAsync(() -> planFeignClient.getPlan(planId));
    }

//    @CircuitBreaker(name="customerService")
    public CompletableFuture<List<Long>> getSpecificFriends(Long phoneNo){

        return CompletableFuture.supplyAsync(() -> friendFamilyFeignClient.getSpecificFriends(phoneNo));
    }

}
