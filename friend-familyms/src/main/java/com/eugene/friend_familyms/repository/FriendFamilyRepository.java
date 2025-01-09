package com.eugene.friend_familyms.repository;

import com.eugene.friend_familyms.entity.FriendFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendFamilyRepository extends JpaRepository<FriendFamily, Integer> {

    @Query("SELECT f FROM FriendFamily f WHERE f.phoneNo = :phoneNo")
    List<FriendFamily> getByPhoneNo(@Param("phoneNo") Long phoneNo);
}
