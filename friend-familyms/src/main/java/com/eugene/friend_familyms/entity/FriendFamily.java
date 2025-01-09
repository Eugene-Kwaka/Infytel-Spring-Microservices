package com.eugene.friend_familyms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "friendfamily")
public class FriendFamily {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "phone_no")
    private Long phoneNo;

    @Column(name = "friend_and_family")
    private Long friendAndFamily;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getFriendAndFamily() {
        return friendAndFamily;
    }

    public void setFriendAndFamily(Long friendAndFamily) {
        this.friendAndFamily = friendAndFamily;
    }
}
