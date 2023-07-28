package com.kaizen.controller;

import com.kaizen.aop.Watcher;
import com.kaizen.api.Translator;
import com.kaizen.domain.Kaizen;
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
    void shouldGetKaizens() throws Exception {
        //Given
        KaizenDto forDtoKaizen = new KaizenDto();
        forDtoKaizen.setKaizenId(1);
        List<KaizenDto> kaizenDto = List.of(forDtoKaizen);

        when(kaizenMapper.mapToKaizenDtoList(new ArrayList<>())).thenReturn(kaizenDto);

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/kaizens")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kaizenId", Matchers.is(1)));
    }


    @Test
    void getRewardById() throws Exception {
        // Given
        int kaizenId = 1;
        Kaizen kaizen = new Kaizen(kaizenId, LocalDate.now(), false, null, "Test problem", "Test solution", false, null, null);
        KaizenDto kaizenDto = new KaizenDto(); // Tworzymy pusty obiekt KaizenDto, ponieważ mockujemy mappera
        kaizenDbService.saveKaizen(kaizen);
        given(kaizenDbService.getKaizen(kaizenId)).willReturn(kaizen);
        given(kaizenMapper.mapToKaizenDto(kaizen)).willReturn(kaizenDto);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/kaizenId/{kaizenId}", kaizenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


        // Then
        verify(kaizenDbService, times(1)).getKaizen(kaizenId);
        verify(kaizenMapper, times(1)).mapToKaizenDto(kaizen);
    }

    @Test
    void getAllKaizens() {
    }

    @Test
    void getKaizensOlderThen() {
    }

    @Test
    void getKaizensCreatedBy() {
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