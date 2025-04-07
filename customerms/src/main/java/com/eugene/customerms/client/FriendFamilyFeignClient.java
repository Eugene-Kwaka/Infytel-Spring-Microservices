package com.eugene.customerms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "friend-familyms",  url="${friend-familyms.url}")
public interface FriendFamilyFeignClient {

    @GetMapping("/customers/{phoneNo}/friends")
    List<Long> getSpecificFriends(@PathVariable("phoneNo") Long phoneNo);
}
