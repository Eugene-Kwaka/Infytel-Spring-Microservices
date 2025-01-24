package com.eugene.friend_familyms.controller;

import com.eugene.friend_familyms.dto.FriendFamilyDTO;
import com.eugene.friend_familyms.service.FriendFamilyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("customers/")
public class FriendFamilyController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FriendFamilyService friendService;

    // Create Friend Family
    @PostMapping("/{phoneNo}/friends")
    public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
        logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
        friendService.saveFriend(phoneNo, friendDTO);
    }

    // Get Specific Friends
    @GetMapping("/{phoneNo}/friends")
    public List<Long> getSpecificFriends(@PathVariable Long phoneNo) {
        logger.info("Get request for customer {}", phoneNo);

//        try{
//            Thread.sleep(5000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        return friendService.getSpecificFriends(phoneNo);
    }
}
