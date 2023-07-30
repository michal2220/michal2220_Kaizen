package com.kaizen.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaizen.controller.KaizenController;
import com.kaizen.controller.RewardController;
import com.kaizen.controller.exception.RewardNotFoundException;
import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.dto.KaizenDto;
import com.kaizen.domain.dto.RewardDto;
import com.kaizen.mapper.RewardMapper;
import com.kaizen.service.dbService.RewardDbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitWebConfig
@WebMvcTest(RewardController.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RewardMapper rewardMapper;
    @MockBean
    private RewardDbService rewardDbService;


    @Test
    void getRewardById() throws Exception {
        //Given
        RewardDto rewardDto = new RewardDto();
        int rewardId = 11011;
        rewardDto.setRewardId(rewardId);

        //When&Then
        when(rewardMapper.mapToRewardDto(any(Reward.class))).thenReturn(rewardDto);
        when(rewardDbService.getReward(rewardId)).thenReturn(new Reward());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/rewards/rewardId/{rewardId}", rewardId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rewardId", Matchers.is(rewardId)));
    }

    @Test
    void getRewardsByName() throws Exception {
        //Given
        String name = "Test name";
        RewardDto rewardDto = new RewardDto();
        rewardDto.setRewardId(11011);
        rewardDto.setName(name);

        //When&Then
        when(rewardMapper.mapToRewardDtoList(new ArrayList<>())).thenReturn(List.of(rewardDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/rewards/rewardName/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rewardId", Matchers.is(11011)));
    }

    @Test
    void getRewardsByPrice() throws Exception {
        //Given
        int price = 100;
        RewardDto rewardDto = new RewardDto();
        rewardDto.setRewardId(11011);
        rewardDto.setPrice(price);

        //When&Then
        when(rewardMapper.mapToRewardDtoList(new ArrayList<>())).thenReturn(List.of(rewardDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/rewards/rewardsPrice/{price}", price)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rewardId", Matchers.is(11011)));
    }

    @Test
    void getRewardsMoreExpensiveThen() throws Exception {
        //Given
        int price = 100;
        RewardDto rewardDto = new RewardDto();
        rewardDto.setRewardId(11011);
        rewardDto.setPrice(price);

        //When&Then
        when(rewardMapper.mapToRewardDtoList(new ArrayList<>())).thenReturn(List.of(rewardDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/rewards/rewardsPrice/{price}", 10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rewardId", Matchers.is(11011)));
    }

    @Test
    void getRewardsMoreLessThen() throws Exception {
        //Given
        int price = 100;
        RewardDto rewardDto = new RewardDto();
        rewardDto.setRewardId(11011);
        rewardDto.setPrice(price);

        //When&Then
        when(rewardMapper.mapToRewardDtoList(new ArrayList<>())).thenReturn(List.of(rewardDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/rewards/rewardsPrice/{price}", 1000)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rewardId", Matchers.is(11011)));
    }

    @Test
    void getAllRewards() throws Exception {
        //Given
        RewardDto rewardDto = new RewardDto();
        rewardDto.setRewardId(11011);

        //When&Then
        when(rewardMapper.mapToRewardDtoList(new ArrayList<>())).thenReturn(List.of(rewardDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/rewards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rewardId", Matchers.is(11011)));
    }


    @Test
    void createReward() throws Exception {
        //Given
        RewardDto rewardDto = new RewardDto();
        Reward reward = new Reward();

        ObjectMapper objectMapper = new ObjectMapper();
        String rewardDtoJson = objectMapper.writeValueAsString(rewardDto);

        //When&Then
        when(rewardMapper.mapToReward(rewardDto)).thenReturn(reward);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/rewards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(rewardDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(rewardDbService, times(1)).saveReward(reward);
    }

    @Test
    void updateReward() throws Exception {
        //Given
        RewardDto rewardDto = new RewardDto();
        Reward reward = new Reward();

        ObjectMapper objectMapper = new ObjectMapper();
        String rewardDtoJson = objectMapper.writeValueAsString(rewardDto);

        //When&Then
        when(rewardMapper.mapToReward(rewardDto)).thenReturn(reward);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/rewards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(rewardDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(rewardDbService, times(1)).saveReward(reward);
    }

    @Test
    void deleteReward() throws Exception {
        //Given
        int rewardId = 11011;

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/rewards/{rewardId}", rewardId))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}