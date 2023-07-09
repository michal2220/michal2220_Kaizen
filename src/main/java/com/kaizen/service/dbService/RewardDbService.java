package com.kaizen.service.dbService;


import com.kaizen.controller.exception.RewardNotFoundException;
import com.kaizen.domain.Reward;
import com.kaizen.service.repository.KaizenRepository;
import com.kaizen.service.repository.RewardRepository;
import jakarta.persistence.NamedQuery;
import jdk.jfr.Name;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
@RequiredArgsConstructor
public class RewardDbService {
    private final RewardRepository rewardRepository;

    public Reward getReward(final int rewardId) throws RewardNotFoundException {
        return rewardRepository.findByRewardId(rewardId);
    }

    public List<Reward> rewardListByPrice(final int price) throws RewardNotFoundException {
        return rewardRepository.findByPrice(price);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Reward saveReward(final Reward reward) {
        return rewardRepository.save(reward);
    }

    public void deleteRewardById(final int rewardId) throws RewardNotFoundException {
        rewardRepository.deleteByRewardId(rewardId);
    }

    public List<Reward> getRewardsMoreExpensiveThen(final int price) throws RewardNotFoundException {
        return rewardRepository.getRewardsMoreExpensiveThan(price);
    }

    public List<Reward> getRewardsLessExpensiveThen(final int price) throws RewardNotFoundException {
        return rewardRepository.getRewardsLessExpensiveThan(price);
    }

}