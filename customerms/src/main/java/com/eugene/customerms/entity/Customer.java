package com.eugene.customerms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "phone_no", nullable = false)
    private Long phoneNo;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 1)
    private Character gender;

    @Column(name = "plan_id", nullable = false)
    private Integer planId;

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    //    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "plan_id")
//    Plan plan;
//
//    @OneToMany(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "phone_no")
//    List<FriendFamily> friends = new ArrayList<>();

//    public Plan getPlan() {
//        return plan;
//    }
//
//    public void setPlan(Plan plan) {
//        this.plan = plan;
//    }
//
//    public List<FriendFamily> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<FriendFamily> friends) {
//        this.friends = friends;
//    }
}

