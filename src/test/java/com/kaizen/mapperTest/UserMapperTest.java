package com.kaizen.mapperTest;

import com.kaizen.domain.User;
import com.kaizen.domain.dto.UserDto;
import com.kaizen.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void mapToUserDto() {
        //Given
        User user = new User("Name", "Lastname", 1);
        UserDto userDtoExpected = new UserDto();
        userDtoExpected.setName("Name");
        userDtoExpected.setLastname("Lastname");
        userDtoExpected.setBrigade(1);

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(userDtoExpected, userDto);
    }

    @Test
    void mapToUser() {
        //Given
        UserDto userDto = new UserDto();
        userDto.setName("Name");
        userDto.setLastname("Lastname");
        userDto.setBrigade(1);

        User userExpected = new User("Name", "Lastname", 1);

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals(userExpected.toString(), user.toString());
    }

    @Test
    void mapToUserDtosList() {
        //Given
        User user = new User("Name", "Lastname", 1);
        UserDto userDtoExpected = new UserDto();
        userDtoExpected.setName("Name");
        userDtoExpected.setLastname("Lastname");
        userDtoExpected.setBrigade(1);

        User user1 = new User("Name", "Lastname", 2);
        UserDto userDtoExpected1 = new UserDto();
        userDtoExpected1.setName("Name");
        userDtoExpected1.setLastname("Lastname");
        userDtoExpected1.setBrigade(2);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        List<UserDto> expectedUserDtoList = new ArrayList<>();
        expectedUserDtoList.add(userDtoExpected);
        expectedUserDtoList.add(userDtoExpected1);

        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtosList(userList);

        //Then
        assertEquals(expectedUserDtoList, userDtoList);
    }
}