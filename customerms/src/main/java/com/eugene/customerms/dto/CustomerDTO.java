package com.eugene.customerms.dto;

import com.eugene.customerms.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {

    private Long phoneNo;
    private String name;
    private Integer age;
    private Character gender;
    private List<Long> friendAndFamily;
    private String password;
    private String address;
    private PlanDTO currentPlan;

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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public List<Long> getFriendAndFamily() {
        return friendAndFamily;
    }

    public void setFriendAndFamily(List<Long> friendAndFamily) {
        this.friendAndFamily = friendAndFamily;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PlanDTO getCurrentPlan() {
        return currentPlan;
    }

    public void setCurrentPlan(PlanDTO currentPlan) {
        this.currentPlan = currentPlan;
    }



    @Override
    public String toString() {
        return "CustomerDTO [phoneNo=" + phoneNo + ", name=" + name + ", age=" + age
                + ", gender=" + gender + ", friendAndFamily=" + friendAndFamily
                + ", password=" + password + ", address=" + address
                + ", currentPlan=" + currentPlan + "]";
    }

    // Converts Entity into DTO
    public static CustomerDTO valueOf(Customer customer) {
        PlanDTO planDTO = new PlanDTO();

        planDTO.setPlanId(customer.getPlanId());

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAge(customer.getAge());
        customerDTO.setGender(customer.getGender());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNo(customer.getPhoneNo());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setCurrentPlan(planDTO);

        return customerDTO;
    }

    // Convert DTO to Entity
    public Customer createEntity(){
        Customer customer = new Customer();

        customer.setAge(this.getAge());
        customer.setGender(this.getGender());
        customer.setName(this.getName());
        customer.setPhoneNo(this.getPhoneNo());
        customer.setAddress(this.getAddress());
        customer.setPassword(this.getPassword());
        customer.setPlanId(this.getCurrentPlan().getPlanId());

        return customer;
    }


}

