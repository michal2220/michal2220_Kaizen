package com.kaizen.service.repository;

import com.kaizen.domain.EventLog;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EventLogRepository extends CrudRepository<EventLog, Integer> {

    EventLog findByEventDescriptionContains(String text);

}
