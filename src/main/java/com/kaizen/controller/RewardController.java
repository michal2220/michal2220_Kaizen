package com.kaizen.controller;

import com.kaizen.controller.exception.RewardNotFoundException;
import com.kaizen.domain.Reward;
import com.kaizen.domain.dto.RewardDto;
import com.kaizen.mapper.RewardMapper;
import com.kaizen.service.dbService.RewardDbService;
import lombok.RequiredArgsConstructor;
import org.atmosphere.config.service.Post;
import org.atmosphere.config.service.Put;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardMapper rewardMapper;
    private final RewardDbService rewardDbService;

    @GetMapping(value = "/rewardId/{rewardId}")
    public ResponseEntity<RewardDto> getRewardById(@PathVariable int rewardId) throws RewardNotFoundException {
        return ResponseEntity.ok(rewardMapper.mapToRewardDto(rewardDbService.getReward(rewardId)));
    }

    @GetMapping(value = "/rewardsPrice/{price}")
    public ResponseEntity<List<RewardDto>> getRewardsByPrice(@PathVariable int price) throws RewardNotFoundException {
        List<Reward> rewards = rewardDbService.rewardListByPrice(price);
        return ResponseEntity.ok(rewardMapper.mapToRewardDtoList(rewards));
    }

    @GetMapping
    public ResponseEntity<List<RewardDto>> getAllRewards() {
        List<Reward> rewards = rewardDbService.getAllRewards();
        return ResponseEntity.ok(rewardMapper.mapToRewardDtoList(rewards));
    }

    @GetMapping(value = "/moreExpensiveThen/{price}")
    public ResponseEntity<List<RewardDto>> getRewardsMoreExpensiveThen(@PathVariable int price) throws RewardNotFoundException {
        List<Reward> rewards = rewardDbService.getRewardsMoreExpensiveThen(price);
        return ResponseEntity.ok(rewardMapper.mapToRewardDtoList(rewards));
    }

    @GetMapping(value = "/lessExpensiveThen/{price}")
    public ResponseEntity<List<RewardDto>> getRewardsMoreLessThen(@PathVariable int price) throws RewardNotFoundException {
        List<Reward> rewards = rewardDbService.getRewardsLessExpensiveThen(price);
        return ResponseEntity.ok(rewardMapper.mapToRewardDtoList(rewards));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReward(@RequestBody RewardDto rewardDto) {
        Reward reward = rewardMapper.mapToReward(rewardDto);
        rewardDbService.saveReward(reward);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<RewardDto> updateReward(@RequestBody RewardDto rewardDto) throws RewardNotFoundException {
        Reward reward = rewardMapper.mapToReward(rewardDto);
        Reward savedReward = rewardDbService.saveReward(reward);
        return ResponseEntity.ok(rewardMapper.mapToRewardDto(savedReward));
    }

    @DeleteMapping(value = "{rewardId}")
    public ResponseEntity<Void> deleteReward(@PathVariable int rewardId) throws RewardNotFoundException {
        rewardDbService.deleteRewardById(rewardId);
        return ResponseEntity.ok().build();
    }
}
