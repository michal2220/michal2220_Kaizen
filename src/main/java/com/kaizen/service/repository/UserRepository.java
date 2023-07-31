package com.kaizen.service.repository;

import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUserId(int userId);

    List<User> findAll();

    @Query
    List<User> findUsersByIdeaCount(final int kaizenCount);

    @Query
    List<User> findUsersByKaizenCountGreaterThan(final int kaizenCount);

    List<User> findUsersByKaizenCountLessThan(final int kaizenCount);

    List<User> findUserByBrigade(final int brigade);

    void deleteByUserId(final int userId);

    @Query
    List<User> findUsersByLastname(String lastname);
}
