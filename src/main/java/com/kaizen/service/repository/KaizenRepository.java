package com.kaizen.service.repository;

import com.kaizen.domain.Kaizen;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface KaizenRepository extends CrudRepository<Kaizen, Integer> {

    Kaizen findByKaizenId(int kaizenId);

    List<Kaizen> findAll();

    List<Kaizen> findKaizenByUserUserId(int userId);

    List<Kaizen> findKaizenByRewardRewardId(int rewardId);

    List<Kaizen> findKaizenByFillingDateBefore(LocalDate date);

    List<Kaizen> findKaizenByUserNameAndUserLastname(String name, String lastname);

    List<Kaizen> findKaizenByCompleted(boolean completed);

}