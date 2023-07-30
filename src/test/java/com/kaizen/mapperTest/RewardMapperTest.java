package com.kaizen.mapperTest;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.dto.RewardDto;
import com.kaizen.mapper.RewardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RewardMapperTest {

    @Autowired
    private RewardMapper rewardMapper;

    @Test
    void mapToRewardDto() {
        //Given
        Kaizen kaizen = new Kaizen();
        List<Kaizen> kaizenList = new ArrayList<>();
        kaizenList.add(kaizen);

        Reward reward = new Reward("name", 100);
        reward.setKaizen(kaizenList);
        RewardDto expectedRewardDto = new RewardDto();
        expectedRewardDto.setName("name");
        expectedRewardDto.setPrice(100);
        expectedRewardDto.setKaizenId(List.of(0));

        //When
        RewardDto rewardDto = rewardMapper.mapToRewardDto(reward);

        //Then
        assertEquals(expectedRewardDto, rewardDto);
    }

    @Test
    void mapToReward() {
        //Given
        RewardDto rewardDto = new RewardDto();
        Reward expectedReward = new Reward();
        expectedReward.setKaizen(new ArrayList<>());

        //When
        Reward reward = rewardMapper.mapToReward(rewardDto);

        //Then
        assertEquals(expectedReward.toString(), reward.toString());
    }

    @Test
    void mapToRewardDtoList() {
        //Given
        Kaizen kaizen = new Kaizen();
        List<Kaizen> kaizenList = new ArrayList<>();

        Reward reward1 = new Reward();
        Reward reward2 = new Reward();
        reward1.setKaizen(kaizenList);
        reward2.setKaizen(kaizenList);

        List<Reward> rewards = new ArrayList<>();
        rewards.add(reward1);
        rewards.add(reward2);

        RewardDto rewardDto1 = new RewardDto();
        rewardDto1.setKaizenId(List.of());

        RewardDto rewardDto2 = new RewardDto();
        rewardDto2.setKaizenId(List.of());

        List<RewardDto> expectedRewardDtoList = new ArrayList<>();
        expectedRewardDtoList.add(rewardDto1);
        expectedRewardDtoList.add(rewardDto2);

        //When
        List<RewardDto> rewardDtoList = rewardMapper.mapToRewardDtoList(rewards);

        //Then
        assertEquals(expectedRewardDtoList, rewardDtoList);
    }
}