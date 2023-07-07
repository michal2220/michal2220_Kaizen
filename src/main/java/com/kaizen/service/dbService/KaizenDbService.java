package com.kaizen.service.dbService;

import com.kaizen.domain.Kaizen;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface KaizenDbService extends CrudRepository<Kaizen, Integer> {

}