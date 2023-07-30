package com.kaizen.aopTest;

import com.kaizen.aop.Watcher;
import com.kaizen.domain.EventLog;
import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.UserDbService;
import com.kaizen.service.repository.EventLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
        //Given
        Kaizen kaizen = new Kaizen(LocalDate.now(),false,"test", "test", false);
        kaizenDbService.saveKaizen(kaizen);

        //When
        int kaizenId = kaizen.getKaizenId();
        watcher.logCreatingKaizen(kaizenId);
        EventLog eventLog = eventLogRepository.findByEventDescriptionContains("CREATED: "+kaizenId);
        int id = eventLog.getEventId();

        //Then
        assertNotNull(eventLog);

        //CleanUp
        eventLogRepository.deleteById(id);
        assertFalse(eventLogRepository.existsById(id));
        kaizenDbService.deleteKaizenById(kaizenId);
    }

    @Test
    void logDeletingKaizen() {
        //Given
        Kaizen kaizen = new Kaizen(LocalDate.now(),false,"test", "test", false);
        kaizenDbService.saveKaizen(kaizen);

        //When
        int kaizenId = kaizen.getKaizenId();
        watcher.logDeletingKaizen(kaizenId);
        EventLog eventLog = eventLogRepository.findByEventDescriptionContains("DELETED: "+kaizenId);
        int id = eventLog.getEventId();

        //Then
        assertNotNull(eventLog);

        //CleanUp
        eventLogRepository.deleteById(id);
        assertFalse(eventLogRepository.existsById(id));
        kaizenDbService.deleteKaizenById(kaizenId);
    }

    @Test
    void logCompletingKaizen() {
        Kaizen kaizen = new Kaizen(LocalDate.now(),false,"test", "test", false);
        kaizen.setReward(new Reward("testName", 100));

        kaizenDbService.saveKaizen(kaizen);


        //When
        int kaizenId = kaizen.getKaizenId();
        watcher.logCompletingKaizen(kaizenId);
        EventLog eventLog = eventLogRepository.findByEventDescriptionContains("REWARDED with: "+kaizen.getReward().getName());
        int id = eventLog.getEventId();

        //Then
        assertNotNull(eventLog);

        //CleanUp
        eventLogRepository.deleteById(id);
        assertFalse(eventLogRepository.existsById(id));
        kaizenDbService.deleteKaizenById(kaizenId);
    }

    @Test
    void logTranslation() {
        //Given
        Kaizen kaizen = new Kaizen(LocalDate.now(),false,"test", "test", false);
        kaizenDbService.saveKaizen(kaizen);

        //When
        int kaizenId = kaizen.getKaizenId();
        watcher.logTranslation(kaizenId, "test");
        EventLog eventLog = eventLogRepository.findByEventDescriptionContains("KAIZEN: "+kaizenId);
        int id = eventLog.getEventId();

        //Then
        assertNotNull(eventLog);

        //CleanUp
        eventLogRepository.deleteById(id);
        assertFalse(eventLogRepository.existsById(id));
        kaizenDbService.deleteKaizenById(kaizenId);
    }
}