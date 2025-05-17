package com.eugene.customerms.service;

import com.eugene.customerms.client.FriendFamilyFeignClient;
import com.eugene.customerms.client.PlanFeignClient;
import com.eugene.customerms.dto.PlanDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import io.vavr.concurrent.Future;

import java.util.Collections;
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
     *  1. If the Feign call fails or times out inside the async block, Resilience4j won't know about it.
     *  2. The circuit breaker might never open or recover.
     *  3. Failures don't trigger the callback function properly.
     *  4. Logging and metrics are broken/incomplete.
     *===================================================================================================================================================
     * To solve this, we apply the following mechanisms to ensure that the circuit breaker works properly even for the feign calls inside CompletableFuture:
     *  1. The @CircuitBreaker annotations are used on synchronous methods, which makes them compatible with Resilience4j even when used inside supplyAsync().
     *  2. The synchronous methods are called inside the supplyAsync() block, ensuring that the circuit breaker is applied correctly.
     *  3. The CompletableFuture.supplyAsync() method is used to run the synchronous methods in a separate thread, allowing for non-blocking behavior.
     *  4. The fallbackPlan(...) and fallbackFriends(...) methods provide graceful fallback behavior when Feign clients fail.
     *  5. These fallback methods match the signature: (OriginalParams..., Throwable) — which is required by Resilience4j.
     *  */


    // -----------------------------
    // Synchronous Plan Service Call with Circuit Breaker
    // -----------------------------

    @CircuitBreaker(name="planService", fallbackMethod = "fallbackPlan")
    public PlanDTO getPlanSync(Integer planId){
        return planFeignClient.getPlan(planId);
    }

    public CompletableFuture<PlanDTO> getPlan(Integer planId){
        return CompletableFuture.supplyAsync(() -> getPlanSync(planId));
    }

    // Fallback method for getPlan
    public PlanDTO fallbackPlan(Integer planId, Throwable throwable) {
        PlanDTO fallback = new PlanDTO();
        fallback.setPlanId(planId);
        fallback.setPlanName("Fallback Plan");
        return fallback;
    }

    // -----------------------------
    // Synchronous Friend-Family Service Call with Circuit Breaker
    // -----------------------------

    @CircuitBreaker(name="friendFamilyService", fallbackMethod = "fallbackFriends")
    public List<Long> getFriendsSync(Long phoneNo){
        return friendFamilyFeignClient.getSpecificFriends(phoneNo);
    }

    public CompletableFuture<List<Long>> getSpecificFriends(Long phoneNo){
        return CompletableFuture.supplyAsync(() -> getFriendsSync(phoneNo));
    }

    // Fallback method for getSpecificFriends
    public List<Long> fallbackFriends(Long phoneNo, Throwable throwable) {
        return Collections.emptyList(); // or return a cached/default list
    }


}
