package com.kaizen.service.repository;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface KaizenRepository extends CrudRepository<Kaizen, Integer> {

    Kaizen findByKaizenId(int kaizenId);

    List<Kaizen> findAll();

    List<Kaizen> findKaizenByUserUserId(int userId);
}