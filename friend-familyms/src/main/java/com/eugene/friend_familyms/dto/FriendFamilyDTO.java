package com.eugene.friend_familyms.dto;


import com.eugene.friend_familyms.entity.FriendFamily;

public class FriendFamilyDTO {

    private Long phoneNo;
    private Long friendAndFamily;

    public FriendFamilyDTO() {
        super();
    }

    public FriendFamilyDTO(long phoneNo, long friendAndFamily) {
        this.phoneNo = phoneNo;
        this.friendAndFamily = friendAndFamily;
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

    // Converts DTO into Entity
    public FriendFamily createFriend() {
        FriendFamily friend = new FriendFamily();

        friend.setPhoneNo(this.getPhoneNo());
        friend.setFriendAndFamily(this.getFriendAndFamily());

        return friend;
    }

    @Override
    public String toString() {
        return "FriendFamilyDTO [phoneNo=" + phoneNo + ", friendAndFamily=" + friendAndFamily + "]";
    }
}
