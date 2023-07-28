package com.kaizen.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    public Kaizen(int kaizenId, LocalDate fillingDate,
                  boolean completed, LocalDate completionDate,
                  String problem, String solution, boolean rewarded,
                  User user, Reward reward) {
        this.kaizenId = kaizenId;
        this.fillingDate = fillingDate;
        this.completed = completed;
        this.completionDate = completionDate;
        this.problem = problem;
        this.solution = solution;
        this.rewarded = rewarded;
        this.user = user;
        this.reward = reward;
    }
    public Kaizen(int kaizenId, LocalDate fillingDate,
                  boolean completed, LocalDate completionDate,
                  String problem, String solution, boolean rewarded,
                  User user) {
        this.kaizenId = kaizenId;
        this.fillingDate = fillingDate;
        this.completed = completed;
        this.completionDate = completionDate;
        this.problem = problem;
        this.solution = solution;
        this.rewarded = rewarded;
        this.user = user;
    }



    public Kaizen(LocalDate fillingDate, boolean completed, String problem, String solution, boolean rewarded) {
        this.fillingDate = fillingDate;
        this.completed = completed;
        this.problem = problem;
        this.solution = solution;
        this.rewarded = rewarded;
    }
}
