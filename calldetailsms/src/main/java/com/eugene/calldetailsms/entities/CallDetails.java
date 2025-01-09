package com.eugene.calldetailsms.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "calldetails")
public class CallDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="called_by", nullable=false)
    private Long calledBy;

    @Column(name="called_to",nullable=false)
    private Long calledTo;

    @Column(name="called_on",nullable=false)
    private LocalDate calledOn;

    @Column(name="duration",nullable=false)
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
}
