package com.kaizen.service.repository;

import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserId(int userId);

    List<User> findAll();
}
