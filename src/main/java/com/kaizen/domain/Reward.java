package com.kaizen.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Reward {
    @Id
    @GeneratedValue
    private int rewardId;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private int price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "kaizen_id")
    private Kaizen kaizen;
}
