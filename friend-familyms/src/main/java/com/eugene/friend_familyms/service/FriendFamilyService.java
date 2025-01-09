package com.eugene.friend_familyms.service;

import com.eugene.friend_familyms.dto.FriendFamilyDTO;
import com.eugene.friend_familyms.entity.FriendFamily;
import com.eugene.friend_familyms.repository.FriendFamilyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendFamilyService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FriendFamilyRepository friendRepository;

    // Create Friend Family
    public void saveFriend(Long phoneNo, FriendFamilyDTO friendDTO) {
        logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);

        friendDTO.setPhoneNo(phoneNo);

        FriendFamily friend = friendDTO.createFriend();

        friendRepository.save(friend);
    }

    // Get Specific Friends
    public List<Long> getSpecificFriends(long phoneNo) {
        List<Long> friendList = new ArrayList<>();

        List<FriendFamily> friends = friendRepository.getByPhoneNo(phoneNo);

        for (FriendFamily friend : friends) {
            friendList.add(friend.getFriendAndFamily());
        }
        return friendList;
    }
}
