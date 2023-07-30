package com.kaizen.mapperTest;

import com.kaizen.controller.exception.RewardNotFoundException;
import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import com.kaizen.domain.dto.KaizenDto;
import com.kaizen.mapper.KaizenMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KaizenMapperTest {

    @Autowired
    private KaizenMapper kaizenMapper;

    @Test
    void mapToKaizenDto() {
        //Given
        Kaizen kaizen = new Kaizen();
        kaizen.setUser(new User());
        kaizen.setReward(new Reward());
        KaizenDto expectedKaizenDto = new KaizenDto();
        expectedKaizenDto.setKaizenId(0);
        expectedKaizenDto.setRewardId(0);

        //When
        KaizenDto kaizenDto = kaizenMapper.mapToKaizenDto(kaizen);

        //Then
        assertEquals(expectedKaizenDto, kaizenDto);
    }

    @Test
    void mapToKaizen() throws RewardNotFoundException {
        //Given
        KaizenDto kaizenDto = new KaizenDto();
        Kaizen expectedKaizen = new Kaizen();

        //When
        Kaizen kaizen = kaizenMapper.mapToKaizen(kaizenDto);

        //Then
        assertEquals(expectedKaizen.toString(), kaizen.toString());
    }

    @Test
    void mapToKaizenDtoList() {
        //Given
        Kaizen kaizen = new Kaizen();
        kaizen.setUser(new User());
        kaizen.setReward(new Reward());
        KaizenDto expectedKaizenDto = new KaizenDto();
        expectedKaizenDto.setKaizenId(0);
        expectedKaizenDto.setRewardId(0);

        List<Kaizen> kaizens = new ArrayList<>();
        kaizens.add(kaizen);

        List<KaizenDto> expectedKaizenDtoList = new ArrayList<>();
        expectedKaizenDtoList.add(expectedKaizenDto);

        //When
        List<KaizenDto> kaizenDtoList = kaizenMapper.mapToKaizenDtoList(kaizens);

        //Then
        assertEquals(expectedKaizenDtoList, kaizenDtoList);
    }
}