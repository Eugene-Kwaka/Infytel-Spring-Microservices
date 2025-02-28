package com.eugene.planms.controller;

import com.eugene.planms.dto.PlanDTO;
import com.eugene.planms.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@EnableAutoConfiguration
@CrossOrigin
public class PlanController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlanService planService;

    @GetMapping("/plans")
    public List<PlanDTO> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/plans/{planId}")
    public PlanDTO getPlan(@PathVariable("planId") Integer planId) {

//        try{
//            Thread.sleep(5000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }

        return planService.getPlan(planId);
    }
}