package com.kaizen.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NamedQueries({
        @NamedQuery(
                name = "Reward.getRewardsMoreExpensiveThan",
                query = "SELECT r FROM Reward r WHERE r.rewardId > :price"
        ),

        @NamedQuery(
                name = "Reward.getRewardsLessExpensiveThan",
                query = "SELECT r FROM Reward r WHERE r.rewardId <= :price"
        ),

})
@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Reward(int rewardId, String name, int price, Kaizen kaizen) {
        this.rewardId = rewardId;
        this.name = name;
        this.price = price;
        this.kaizen = kaizen;
    }

    public Reward(String name, int price) {
        this.rewardId = rewardId;
        this.name = name;
        this.price = price;
    }
}
