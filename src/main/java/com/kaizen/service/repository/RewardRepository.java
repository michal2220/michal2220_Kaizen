package com.kaizen.service.repository;

import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface RewardRepository extends CrudRepository<Reward, Integer> {

    Reward findByRewardId(final int rewardId);

    List<Reward> findAll();
}