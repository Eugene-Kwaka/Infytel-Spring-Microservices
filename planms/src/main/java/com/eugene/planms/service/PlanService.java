package com.eugene.planms.service;


import com.eugene.planms.dto.PlanDTO;
import com.eugene.planms.entity.Plan;
import com.eugene.planms.repository.PlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlanRepository planRepository;

    public List<PlanDTO> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        List<PlanDTO> planDTOs = new ArrayList<>();

        for (Plan plan : plans) {
            PlanDTO planDTO = PlanDTO.valueOf(plan);
            planDTOs.add(planDTO);
        }

        logger.info("Plan details : {}", planDTOs);
        return planDTOs;
    }

    public PlanDTO getPlan(Integer planId) {
        Plan plan = planRepository.findById(planId).get();
        PlanDTO planDTO = PlanDTO.valueOf(plan);
        return planDTO;
    }
}
