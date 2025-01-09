package com.eugene.calldetailsms.dto;

import com.eugene.calldetailsms.entities.CallDetails;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CallDetailsDTO {

    private Long calledBy;

    private Long calledTo;

    private LocalDate calledOn;

    private Integer duration;

    public Long getCalledBy() {
        return calledBy;
    }

    public void setCalledBy(Long calledBy) {
        this.calledBy = calledBy;
    }

    public Long getCalledTo() {
        return calledTo;
    }

    public void setCalledTo(Long calledTo) {
        this.calledTo = calledTo;
    }

    public LocalDate getCalledOn() {
        return calledOn;
    }

    public void setCalledOn(LocalDate calledOn) {
        this.calledOn = calledOn;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    // Converts Entity to DTO
    public static CallDetailsDTO valueOf(CallDetails callDetails){
        CallDetailsDTO callsDTO = new CallDetailsDTO();

        callsDTO.setCalledBy(callDetails.getCalledBy());
        callsDTO.setCalledTo(callDetails.getCalledTo());
        callsDTO.setCalledOn(callDetails.getCalledOn());
        callsDTO.setDuration(callDetails.getDuration());

        return callsDTO;

    }

}
