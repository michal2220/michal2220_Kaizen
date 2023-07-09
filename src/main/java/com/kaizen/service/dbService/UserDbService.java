package com.kaizen.service.dbService;

import com.kaizen.domain.User;
import com.kaizen.service.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public User getUser (final int userId) {
        return userRepository.findByUserId(userId);
    }
}
