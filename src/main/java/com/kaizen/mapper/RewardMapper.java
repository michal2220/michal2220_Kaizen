package com.kaizen.mapper;

import com.kaizen.domain.Reward;
import com.kaizen.domain.dto.RewardDto;
import com.kaizen.service.dbService.KaizenDbService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class RewardMapper {

    private KaizenDbService kaizenDbService;

    public RewardDto mapToRewardDto(final Reward reward) {
        return new RewardDto(
                reward.getRewardId(),
                reward.getName(),
                reward.getPrice(),
                reward.getKaizen().getKaizenId()
        );
    }

    public Reward mapToReward(final RewardDto rewardDto) {
        return new Reward(
                rewardDto.getRewardId(),
                rewardDto.getName(),
                rewardDto.getPrice(),
                kaizenDbService.getKaizen(rewardDto.getKaizenId())
        );
    }

    public List<RewardDto> mapToRewardDtoList(final List<Reward> rewards) {
        return rewards.stream()
                .map(this::mapToRewardDto)
                .collect(Collectors.toList());
    }
}
