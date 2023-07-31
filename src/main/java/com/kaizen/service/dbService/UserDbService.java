package com.kaizen.service.dbService;

import com.kaizen.controller.exception.UserNotFoundException;
import com.kaizen.domain.User;
import com.kaizen.service.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public User getUser(final int userId) throws UserNotFoundException {
        return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersWithKaizenQuantity(final int kaizenCount) {
        return userRepository.findUsersByIdeaCount(kaizenCount);
    }

    public List<User> getUsersWithLessThenKaizenQuantity(final int kaizenCount) {
        return userRepository.findUsersByKaizenCountLessThan(kaizenCount);
    }

    public List<User> getUsersWithMoreThenKaizenQuantity(final int kaizenCount) {
        return userRepository.findUsersByKaizenCountGreaterThan(kaizenCount);
    }

    public List<User> getUsersByBrigade(final int brigade) {
        return userRepository.findUserByBrigade(brigade);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(final int userId) {
        userRepository.deleteById(userId);
    }

    public List<User> findByLastname(final String lastname) {
        return userRepository.findUsersByLastname(lastname);
    }
}
