package com.kaizen.service.dbService;


import com.kaizen.domain.Reward;
import com.kaizen.service.repository.KaizenRepository;
import com.kaizen.service.repository.RewardRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class RewardDbService {
    private final RewardRepository rewardRepository;

    public Reward getReward (final int rewardId) {
        return rewardRepository.findByRewardId(rewardId);
    }
}