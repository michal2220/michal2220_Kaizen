package com.kaizen.mapper;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.User;
import com.kaizen.domain.dto.UserDto;
import com.kaizen.service.dbService.KaizenDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final KaizenDbService kaizenDbService;


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
                kaizenDbService.getKaizenByUser(userDto.getUserId())
        );
    }

    public List<UserDto> mapToUserDtosList(final List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
