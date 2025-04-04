package com.eugene.customerms.client;

import com.eugene.customerms.dto.PlanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "planms", url="${planms.url}" )
public interface PlanFeignClient {

    @GetMapping("/plans/{planId}")
    PlanDTO getPlan(@PathVariable("planId") Integer planId);
}

//url = "${planms.url}"