package com.kaizen.aop;

import com.kaizen.domain.EventLog;
import com.kaizen.domain.User;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.UserDbService;
import com.kaizen.service.repository.EventLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WatcherTest {

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private KaizenDbService kaizenDbService;

    @Autowired
    private EventLogRepository eventLogRepository;

    @Autowired
    private Watcher watcher;

    @Test
    void logSavingUser() {
        //Given
        User user = new User("a","a",1);
        userDbService.saveUser(user);

        //When
        int userId = user.getUserId();
        watcher.logSavingUser(userId);
        EventLog eventLog = eventLogRepository.findByEventDescriptionContains("CREATED: "+userId);
        int id = eventLog.getEventId();

        //Then
        assertNotNull(eventLog);

        //CleanUp
        eventLogRepository.deleteById(id);
        assertFalse(eventLogRepository.existsById(id));
        userDbService.deleteUserById(userId);
    }

    @Test
    void logDeleteUser() {
        //Given
        User user = new User("a","a",1);
        userDbService.saveUser(user);

        //When
        int userId = user.getUserId();
        watcher.logDeleteUser(userId);
        EventLog eventLog = eventLogRepository.findByEventDescriptionContains("DELETED: "+userId);
        int id = eventLog.getEventId();

        //Then
        assertNotNull(eventLog);

        //CleanUp
        eventLogRepository.deleteById(id);
        assertFalse(eventLogRepository.existsById(id));
        userDbService.deleteUserById(userId);
    }

    @Test
    void logCreatingKaizen() {

    }

    @Test
    void logDeletingKaizen() {
    }

    @Test
    void logCompletingKaiden() {
    }

    @Test
    void logTranslation() {
    }
}