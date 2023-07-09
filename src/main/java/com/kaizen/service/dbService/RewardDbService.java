package com.kaizen.service.dbService;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RewardDbService extends CrudRepository<Reward, Integer> {

}