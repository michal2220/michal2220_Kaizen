package com.kaizen.service.dbService;

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

    public User getUser(final int userId) {
        return userRepository.findByUserId(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersWithKaizenQuantity(final int kaizenQuantity) {
        return userRepository.findUsersByIdeaCount(kaizenQuantity);
    }

    public List<User> getUsersWithLessThenKaizenQuantity(final int kaizenQuantity) {
        return userRepository.findUsersByKaizenCountLessThan(kaizenQuantity);
    }

    public List<User> getUsersWithMoreThenKaizenQuantity(final int kaizenQuantity) {
        return userRepository.findUsersByKaizenCountGreaterThan(kaizenQuantity);
    }

    public List<User> getUsersByBrigade(final int brigade) {
        return userRepository.findUserByBrigade(brigade);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(final int userId){
        userRepository.deleteById(userId);
    }
}
