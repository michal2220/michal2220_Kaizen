package com.kaizen.domain;


import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reward_id")
    private Reward reward;
}
