package com.kaizen.service.repository;

import com.kaizen.domain.Reward;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RewardRepository extends CrudRepository<Reward, Integer> {

    Reward findByRewardId(final int rewardId);
}