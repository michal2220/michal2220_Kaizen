package com.kaizen.service.dbService;

import com.kaizen.service.repository.EventLogRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class EventLogDbService {
    private final EventLogRepository EventLogRepository;
}