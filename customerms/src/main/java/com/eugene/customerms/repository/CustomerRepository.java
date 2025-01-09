package com.eugene.customerms.repository;

import com.eugene.customerms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByPhoneNo(Long phoneNo);
}
