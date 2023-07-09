package com.kaizen.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RewardDto {

    private int rewardId;
    private String name;
    private int price;
    private List<Integer> kaizenId;

    public RewardDto(int rewardId, String name, int price, List<Integer> kaizenId) {
        this.rewardId = rewardId;
        this.name = name;
        this.price = price;
        this.kaizenId = kaizenId;
    }
}
