package com.kaizen.service.repository;

import com.kaizen.domain.AuditLog;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Integer> {
}
