package com.kaizen.service.dbService;


import com.kaizen.controller.exception.RewardNotFoundException;
import com.kaizen.domain.Reward;
import com.kaizen.service.repository.RewardRepository;
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
        return rewardRepository.findByRewardId(rewardId).orElseThrow(RewardNotFoundException::new);
    }

    public List<Reward> rewardListByPrice(final int price) {
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

    public List<Reward> getRewardsMoreExpensiveThen(final int price) {
        return rewardRepository.getRewardsMoreExpensiveThan(price);
    }

    public List<Reward> getRewardsLessExpensiveThen(final int price) {
        return rewardRepository.getRewardsLessExpensiveThan(price);
    }

    public List<Reward> getRewardsByName (final String name) {
        return rewardRepository.findByNameContaining(name);
    }

}