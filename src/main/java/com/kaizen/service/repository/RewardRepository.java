package com.kaizen.service.repository;

import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RewardRepository extends CrudRepository<Reward, Integer> {

    Optional<Reward> findByRewardId(final int rewardId);

    List<Reward> findAll();

    List<Reward> findByPrice(final int price);

    void deleteByRewardId(final int rewardId);

    @Query
    List<Reward> getRewardsMoreExpensiveThan(@Param("price") int price);

    @Query
    List<Reward> getRewardsLessExpensiveThan(@Param("price") int price);

    List<Reward> findByNameContaining (final String name);
}