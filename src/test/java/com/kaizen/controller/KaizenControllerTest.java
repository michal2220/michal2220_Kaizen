package com.kaizen.controller;

import com.kaizen.aop.Watcher;
import com.kaizen.api.Translator;
import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import com.kaizen.domain.dto.KaizenDto;
import com.kaizen.mapper.KaizenMapper;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.infoToKaizen.KaizenMailService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@SpringJUnitWebConfig
@WebMvcTest(KaizenController.class)
class KaizenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KaizenDbService kaizenDbService;
    @MockBean
    private KaizenMapper kaizenMapper;

    @MockBean
    private KaizenMailService kaizenMailService;

    @MockBean
    private Translator translator;

    @MockBean
    private Watcher watcher;

    @Test
    void getRewardById() throws Exception {
        //Given
        KaizenDto forDtoKaizen = new KaizenDto();
        int kaizenId = 11011;
        forDtoKaizen.setKaizenId(kaizenId);

        //When&Then
        when(kaizenMapper.mapToKaizenDto(any(Kaizen.class))).thenReturn(forDtoKaizen);
        when(kaizenDbService.getKaizen(kaizenId)).thenReturn(new Kaizen());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/kaizens/kaizenId/{kaizenId}", kaizenId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.kaizenId", Matchers.is(kaizenId)));
    }

    @Test
    void getAllKaizens() throws Exception {
        //Given
        KaizenDto kaizenDto = new KaizenDto();
        kaizenDto.setKaizenId(11011);

        //When&Then
        when(kaizenMapper.mapToKaizenDtoList(new ArrayList<>())).thenReturn(List.of(kaizenDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/kaizens")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kaizenId", Matchers.is(11011)));
    }

    @Test
    void getKaizensOlderThen() throws Exception {
        // Given
        LocalDate date = LocalDate.of(2023,05,05);
        KaizenDto kaizen1 = new KaizenDto();
        KaizenDto kaizen2 = new KaizenDto();
        kaizen1.setKaizenId(11011);
        kaizen2.setKaizenId(11012);
        kaizen1.setFillingDate(LocalDate.of(2023,03,04));
        kaizen2.setFillingDate(LocalDate.of(2023,03,04));
        List<KaizenDto> kaizenList = List.of(kaizen1, kaizen2);

        // When & Then
        when(kaizenMapper.mapToKaizenDtoList(new ArrayList<>())).thenReturn(kaizenList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/kaizens/olderThen/{date}", date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kaizenId", Matchers.is(11011)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].kaizenId", Matchers.is(11012)));
    }

    @Test
    void getKaizensCreatedBy() {
        //When
        KaizenDto kaizenDto = new KaizenDto();


    }

    @Test
    void translateKaizenById() {
    }

    @Test
    void createKaizen() {
    }

    @Test
    void updateKaizen() {
    }

    @Test
    void markAsCompleted() {
    }

    @Test
    void deleteKaizen() {
    }
}