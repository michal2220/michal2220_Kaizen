package com.kaizen.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Reward.getRewardsMoreExpensiveThan",
                query = "SELECT r FROM Reward r WHERE r.rewardId > :price"
        ),

        @NamedQuery(
                name = "Reward.getRewardsLessExpensiveThan",
                query = "SELECT r FROM Reward r WHERE r.rewardId <= :price"
        )
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

    @OneToMany(
            targetEntity = Kaizen.class,
            mappedBy = "reward",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Kaizen> kaizen;

    public Reward(int rewardId, String name, int price, List<Kaizen> kaizen) {
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

    @Override
    public String toString() {
        return "Reward{" +
                "rewardId=" + rewardId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", kaizen=" + kaizen +
                '}';
    }
}
