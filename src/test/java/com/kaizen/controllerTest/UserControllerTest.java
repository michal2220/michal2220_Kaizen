package com.kaizen.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaizen.aop.Watcher;
import com.kaizen.controller.UserController;
import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import com.kaizen.domain.dto.RewardDto;
import com.kaizen.domain.dto.UserDto;
import com.kaizen.mapper.UserMapper;
import com.kaizen.service.dbService.UserDbService;
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
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDbService userDbService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private Watcher watcher;

    @Test
    void getUserById() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        int userId = 11011;
        userDto.setUserId(userId);

        //When&Then
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);
        when(userDbService.getUser(userId)).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users/userId/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(userId)));
    }

    @Test
    void findByLastname() throws Exception {
        //Given
        int userId = 11011;
        String lastname = "Lastname";
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setLastname(lastname);

        //When&Then
        when(userMapper.mapToUserDtosList(new ArrayList<>())).thenReturn(List.of(userDto));
        when(userDbService.getUser(userId)).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users/userLastname/{lastname}", lastname)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname", Matchers.is(lastname)));
    }

    @Test
    void getUsersByKaizenQuantity() throws Exception {
        //Given
        int userId = 11011;
        List<Integer> kaizenList = new ArrayList<>();
        kaizenList.add(1);
        kaizenList.add(2);
        kaizenList.add(3);
        int kaizenCount = 3;
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setKaizenList(kaizenList);

        //When&Then
        when(userMapper.mapToUserDtosList(new ArrayList<>())).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users/kaizenQuantity/{kaizenCount}", kaizenCount)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kaizenList", Matchers.is(kaizenList)));
    }

    @Test
    void getUsersKaizenQuantityLessThen() throws Exception {
        //Given
        int userId = 11011;
        List<Integer> kaizenList = new ArrayList<>();
        kaizenList.add(1);
        kaizenList.add(2);
        kaizenList.add(3);
        int kaizenCount = 10;
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setKaizenList(kaizenList);

        //When&Then
        when(userMapper.mapToUserDtosList(new ArrayList<>())).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users/lessThen/{kaizenCount}", kaizenCount)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kaizenList", Matchers.is(kaizenList)));
    }

    @Test
    void getUsersKaizenQuantityMoreThen() throws Exception {
        //Given
        int userId = 11011;
        List<Integer> kaizenList = new ArrayList<>();
        kaizenList.add(1);
        kaizenList.add(2);
        kaizenList.add(3);
        int kaizenCount = 2;
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setKaizenList(kaizenList);

        //When&Then
        when(userMapper.mapToUserDtosList(new ArrayList<>())).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users/moreThen/{kaizenCount}", kaizenCount)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].kaizenList", Matchers.is(kaizenList)));
    }

    @Test
    void getUsersByBrigade() throws Exception {
        //Given
        int userId = 11011;
        int brigade = 222;
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setBrigade(222);

        //When&Then
        when(userMapper.mapToUserDtosList(new ArrayList<>())).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users/brigade/{brigade}", brigade)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brigade", Matchers.is(brigade)));
    }

    @Test
    void getAllUsers() throws Exception {
        //Given
        int userId = 11011;
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);

        //When&Then
        when(userMapper.mapToUserDtosList(new ArrayList<>())).thenReturn(List.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(userId)));
    }

    @Test
    void createUser() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        User user = new User();

        ObjectMapper objectMapper = new ObjectMapper();
        String rewardDtoJson = objectMapper.writeValueAsString(userDto);

        //When&Then
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(rewardDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userDbService, times(1)).saveUser(user);
    }

    @Test
    void updateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        User user = new User();

        ObjectMapper objectMapper = new ObjectMapper();
        String rewardDtoJson = objectMapper.writeValueAsString(userDto);

        //When&Then
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(rewardDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userDbService, times(1)).saveUser(user);
    }

    @Test
    void deleteUser() throws Exception {
        //Given
        int userId = 11011;

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userDbService, times(1)).deleteUserById(userId);
    }
}