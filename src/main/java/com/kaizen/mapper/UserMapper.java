package com.kaizen.mapper;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.User;
import com.kaizen.domain.dto.UserDto;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.UserDbService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserMapper {

    private KaizenDbService kaizenDbService;

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getLastname(),
                user.getBrigade(),
                user.getKaizen().stream().map(Kaizen::getKaizenId).collect(Collectors.toList())
        );
    }

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getLastname(),
                userDto.getBrigade(),
                kaizenDbService.getAllKaizens().stream()
                        .filter(k -> k.getUser().equals(userDto.getUserId()))
                        .collect(Collectors.toList())
        );
    }

    public List<UserDto> userDtosList(final List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
