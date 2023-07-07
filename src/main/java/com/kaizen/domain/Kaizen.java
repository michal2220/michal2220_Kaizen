package com.kaizen.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Kaizen {

    @Id
    @GeneratedValue
    private int kaizenId;

    @Column
    @NotNull
    private LocalDate fillingDate;

    @Column
    private boolean completed;

    @Column
    private LocalDate completionDate;

    @Column
    @NotNull
    private String problem;

    @Column
    @NotNull
    private String solution;

    @Column
    private boolean rewarded;
}
