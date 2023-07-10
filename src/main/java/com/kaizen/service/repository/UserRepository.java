package com.kaizen.service.repository;

import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserId(int userId);

    List<User> findAll();

    @Query
    List<User> findUsersByIdeaCount(final int kaizenQuantity);

    @Query
    List<User> findUsersByKaizenCountGreaterThan(final int kaizenQuantity);

    List<User> findUsersByKaizenCountLessThan(final int kaizenQuantity);

    List<User> findUserByBrigade(final int brigade);

    void deleteByUserId(final int userId);
}
