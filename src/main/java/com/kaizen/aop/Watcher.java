package com.kaizen.aop;

import com.kaizen.domain.EventLog;
import com.kaizen.domain.Kaizen;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.UserDbService;
import com.kaizen.service.repository.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Aspect
public class Watcher {

    private final EventLogRepository eventLogRepository;

    private final UserDbService userDbService;

    private final KaizenDbService kaizenDbService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);

    public Watcher(EventLogRepository eventLogRepository, UserDbService userDbService, KaizenDbService kaizenDbService) {
        this.eventLogRepository = eventLogRepository;
        this.userDbService = userDbService;
        this.kaizenDbService = kaizenDbService;
    }


    public void saveToLogDatabase(String eventString) {
        EventLog event = new EventLog();
        event.setEventDate(LocalDate.now());
        event.setEventTime(LocalTime.now());
        event.setEventDescription(eventString);

        eventLogRepository.save(event);
    }

    public void logSavingUser(int userId) {

        UserEventDescriptionBuilder userEventDescriptionBuilder1 = buildBasicUserDescription(userId);
        String eventString = "CREATED: " + userEventDescriptionBuilder1;

        saveToLogDatabase(eventString);
    }

    public void logCreatingKaizen(int kaizenId) {
        LOGGER.info("IN WATCHER: " + kaizenId);
        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder = buildBasicKaizenDescription(kaizenId);
        String eventString = "CREATED: " + kaizenEventDescriptionBuilder;

        saveToLogDatabase(eventString);
    }

    public void logDeleteUser(int userId) {

        UserEventDescriptionBuilder userEventDescriptionBuilder1 = buildBasicUserDescription(userId);
        String eventString = "DELETED: " + userEventDescriptionBuilder1;

        saveToLogDatabase(eventString);
    }

    public void logDeletingKaizen(int kaizenId) {

        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder = buildBasicKaizenDescription(kaizenId);
        String eventString = "DELETED: " + kaizenEventDescriptionBuilder;

        saveToLogDatabase(eventString);
    }

    public void logCompletingKaizen(int kaizenId) {

        Kaizen kaizen = kaizenDbService.getKaizen(kaizenId);

        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder2 = buildKaizenStatusChange(kaizenId);

        String eventString = "REWARDED with: " + kaizen.getReward().getName() + " kaizen: " + kaizenEventDescriptionBuilder2;

        saveToLogDatabase(eventString);
    }

    public void logTranslation(int kaizenId, String translation) {

        Kaizen kaizen = kaizenDbService.getKaizen(kaizenId);

        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder3 = new KaizenEventDescriptionBuilder.Builder()
                .eventId(kaizen.getKaizenId())
                .kaizenProblem(kaizen.getProblem())
                .build();

        String eventString = "KAIZEN: " + kaizenEventDescriptionBuilder3
                + " was translated to: " + translation;

        saveToLogDatabase(eventString);
    }

    private KaizenEventDescriptionBuilder buildBasicKaizenDescription(int kaizenId) {
        Kaizen kaizen = kaizenDbService.getKaizen(kaizenId);
        return new KaizenEventDescriptionBuilder.Builder()
                .eventId(kaizen.getKaizenId())
                .kaizenProblem(kaizen.getProblem())
                .kaizenSolution(kaizen.getSolution())
                .kaizenCompleted(kaizen.isCompleted())
                .kaizenIsRewarded(kaizen.isRewarded())
                .kaizenCompletionDate(kaizen.getCompletionDate())
                .build();
    }

    private KaizenEventDescriptionBuilder buildKaizenStatusChange(int kaizenId) {
        Kaizen kaizen = kaizenDbService.getKaizen(kaizenId);
        return new KaizenEventDescriptionBuilder.Builder()
                .eventId(kaizen.getKaizenId())
                .kaizenCompleted(kaizen.isCompleted())
                .build();
    }

    public UserEventDescriptionBuilder buildBasicUserDescription(int userId) {
        return new UserEventDescriptionBuilder.Builder()
                .eventId(userDbService.getUser(userId).getUserId())
                .userName(userDbService.getUser(userId).getName())
                .userLastname(userDbService.getUser(userId).getLastname())
                .userBrigade(userDbService.getUser(userId).getBrigade())
                .build();
    }
}
