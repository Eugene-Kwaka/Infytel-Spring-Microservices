package com.eugene.calldetailsms.repository;

import com.eugene.calldetailsms.entities.CallDetails;
import org.aspectj.weaver.ast.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallDetailsRepository extends JpaRepository<CallDetails, Long> {

    @Query("SELECT c FROM CallDetails c WHERE c.calledBy = :calledBy")
    List<CallDetails> findByCalledBy(@Param("calledBy") Long calledBy);
}
