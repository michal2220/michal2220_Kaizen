package com.kaizen.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaizen.aop.Watcher;
import com.kaizen.api.Translator;
import com.kaizen.controller.KaizenController;
import com.kaizen.domain.Kaizen;
import com.kaizen.domain.dto.KaizenDto;
import com.kaizen.domain.dto.UserDto;
import com.kaizen.mapper.KaizenMapper;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.infoToKaizen.KaizenMailService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


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
    void getKaizenById() throws Exception {
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
        LocalDate date = LocalDate.of(2023, 5, 5);
        KaizenDto kaizen1 = new KaizenDto();
        KaizenDto kaizen2 = new KaizenDto();
        kaizen1.setKaizenId(11011);
        kaizen2.setKaizenId(11012);
        kaizen1.setFillingDate(LocalDate.of(2023, 3, 4));
        kaizen2.setFillingDate(LocalDate.of(2023, 3, 4));
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
    void getKaizensCreatedBy() throws Exception {
        //Given
        UserDto user = new UserDto();
        user.setUserId(222);
        user.setName("Name");
        user.setLastname("Lastname");

        KaizenDto kaizen = new KaizenDto();
        kaizen.setKaizenId(11011);
        kaizen.setUserId(222);

        //When&Then
        when(kaizenMapper.mapToKaizenDtoList(new ArrayList<>())).thenReturn(List.of(kaizen));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/kaizens/creator")
                        .param("name", "Name")
                        .param("lastname", "Lastname")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kaizenId", Matchers.is(11011)));
    }

    @Test
    void translateKaizenById() throws Exception {
        //Given
        String problem = "Problem do testowania";
        String translation = "Problem for testing";
        KaizenDto kaizen = new KaizenDto();
        kaizen.setKaizenId(11011);
        kaizen.setProblem(problem);

        Kaizen kaizen1 = new Kaizen();
        kaizen1.setKaizenId(11011);
        kaizen1.setProblem(problem);


        //When&Then
        when(kaizenDbService.getKaizen(11011)).thenReturn(kaizen1);
        when(translator.doTranslate(problem)).thenReturn(translation);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/kaizens/translate/{kaizenId}", 11011)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(translation));
    }

    @Test
    void createKaizen() throws Exception {
        //Given
        KaizenDto kaizenDto = new KaizenDto();
        kaizenDto.setKaizenId(11011);

        Kaizen kaizen = new Kaizen();
        kaizen.setKaizenId(11011);

        ObjectMapper objectMapper = new ObjectMapper();
        String kaizenDtoJson = objectMapper.writeValueAsString(kaizenDto);

        //When&Then
        when(kaizenMapper.mapToKaizen(kaizenDto)).thenReturn(kaizen);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/kaizens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(kaizenDtoJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(kaizenDbService, times(1)).saveKaizen(kaizen);
    }

    @Test
    void updateKaizen() throws Exception {
        //Given
        KaizenDto kaizenDto = new KaizenDto();
        Kaizen kaizen = new Kaizen();

        ObjectMapper objectMapper = new ObjectMapper();
        String kaizenDtoJson = objectMapper.writeValueAsString(kaizenDto);

        //When&Then
        when(kaizenMapper.mapToKaizen(kaizenDto)).thenReturn(kaizen);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/kaizens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(kaizenDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void markAsCompleted() throws Exception {
        //Given
        KaizenDto kaizenDto = new KaizenDto();
        kaizenDto.setKaizenId(11011);
        kaizenDto.setCompleted(false);

        Kaizen kaizen = new Kaizen();

        //When&Then
        when(kaizenMapper.mapToKaizen(kaizenDto)).thenReturn(kaizen);
        when(kaizenDbService.getKaizen(11011)).thenReturn(kaizen);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/kaizens/markAsCompleted/{kaizenId}", 11011)
                        .queryParam("completionDate", LocalDate.now().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteKaizen() throws Exception {
        //Given
        int kaizenId = 11011;

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/kaizens/{kaizenId}", kaizenId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(kaizenDbService, times(1)).deleteKaizenById(kaizenId);
    }
}