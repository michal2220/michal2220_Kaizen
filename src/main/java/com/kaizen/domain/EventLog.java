package com.kaizen.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EventLog {

    @Id
    @GeneratedValue
    private int eventId;

    @Column
    private LocalDate eventDate;

    @Column
    private LocalTime eventTime;

    @Column(length = 1000)
    private String eventDescription;

    public EventLog(LocalDate eventDate, LocalTime eventTime, String eventDescription) {
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
    }
}
