package com.kaizen.service.repository;

import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserId(int userId);

}
