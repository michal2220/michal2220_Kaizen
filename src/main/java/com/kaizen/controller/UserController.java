package com.kaizen.controller;

import com.kaizen.aop.Watcher;
import com.kaizen.controller.exception.UserNotFoundException;
import com.kaizen.domain.User;
import com.kaizen.domain.dto.UserDto;
import com.kaizen.mapper.UserMapper;
import com.kaizen.service.dbService.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;
    private final Watcher watcher;
    int userToWatcher;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(userDbService.getUser(userId)));
    }

    @GetMapping("/userLastname/{lastname}")
    public ResponseEntity<List<UserDto>> findByLastname(@PathVariable String lastname) throws UserNotFoundException {
        List<User> users = userDbService.findByLastname(lastname);
        return ResponseEntity.ok(userMapper.mapToUserDtosList(users));
    }

    @GetMapping("/kaizenQuantity/{kaizenCount}")
    public ResponseEntity<List<UserDto>> getUsersByKaizenQuantity(@PathVariable int kaizenCount) {
        List<User> users = userDbService.getUsersWithKaizenQuantity(kaizenCount);
        return ResponseEntity.ok(userMapper.mapToUserDtosList(users));
    }

    @GetMapping("/lessThen/{kaizenCount}")
    public ResponseEntity<List<UserDto>> getUsersKaizenQuantityLessThen(@PathVariable int kaizenCount) {
        List<User> users = userDbService.getUsersWithLessThenKaizenQuantity(kaizenCount);
        return ResponseEntity.ok(userMapper.mapToUserDtosList(users));
    }

    @GetMapping("/moreThen/{kaizenCount}")
    public ResponseEntity<List<UserDto>> getUsersKaizenQuantityMoreThen(@PathVariable int kaizenCount) {
        List<User> users = userDbService.getUsersWithMoreThenKaizenQuantity(kaizenCount);
        return ResponseEntity.ok(userMapper.mapToUserDtosList(users));
    }

    @GetMapping("/brigade/{brigade}")
    public ResponseEntity<List<UserDto>> getUsersByBrigade(@PathVariable int brigade) {
        List<User> users = userDbService.getUsersByBrigade(brigade);
        return ResponseEntity.ok(userMapper.mapToUserDtosList(users));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userDbService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtosList(users));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.saveUser(user);
        watcher.logSavingUser(user.getUserId());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userDbService.saveUser(user);
        watcher.logSavingUser(user.getUserId());
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        watcher.logDeleteUser(userId);
        userDbService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}