package com.kaizen.aop;

import com.kaizen.domain.EventLog;
import com.kaizen.domain.Kaizen;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.UserDbService;
import com.kaizen.service.repository.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
@Aspect
public class Watcher {

    private final EventLogRepository eventLogRepository;

    private final UserDbService userDbService;

    private final KaizenDbService kaizenDbService;


    public void saveToLogDatabase(String eventString) {
        EventLog event = new EventLog();
        event.setEventDate(LocalDate.now());
        event.setEventTime(LocalTime.now());
        event.setEventDescription(eventString);

        eventLogRepository.save(event);
    }

    public void logSavingUser(int userId) {

        UserEventDescriptionBuilder userEventDescriptionBuilder1 = buildBasicUserDescription(userId);
        String eventString = "CREATED: " + userEventDescriptionBuilder1.toString();

        saveToLogDatabase(eventString);
    }

    public void logDeleteUser(int userId) {

        UserEventDescriptionBuilder userEventDescriptionBuilder1 = buildBasicUserDescription(userId);
        String eventString = "DELETED: " + userEventDescriptionBuilder1.toString();

        saveToLogDatabase(eventString);
    }

    public void logCreatingKaizen(int kaizenId) {

        Kaizen kaizen = kaizenDbService.getKaizen(kaizenId);
        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder = buildBasicKaizenDescription(kaizenId);
        String eventString = "CREATED: " + kaizenEventDescriptionBuilder.toString();

        saveToLogDatabase(eventString);
    }

    public void logDeletingKaizen(int kaizenId) {

        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder = buildBasicKaizenDescription(kaizenId);
        String eventString = "DELETED: " + kaizenEventDescriptionBuilder.toString();

        saveToLogDatabase(eventString);
    }

    public void logCompletingKaizen(int kaizenId) {

        Kaizen kaizen = kaizenDbService.getKaizen(kaizenId);

        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder2 = new KaizenEventDescriptionBuilder.Builder()
                .eventId(kaizen.getKaizenId())
                .kaizenCompletionDate(kaizen.getCompletionDate())
                .kaizenIsRewarded(kaizen.isRewarded())
                .build();

        String eventString = "REWARDED with: " + kaizen.getReward().getName() + " kaizen: " + kaizenEventDescriptionBuilder2.toString();

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
        KaizenEventDescriptionBuilder kaizenEventDescriptionBuilder = new KaizenEventDescriptionBuilder.Builder()
                .eventId(kaizenDbService.getKaizen(kaizenId).getKaizenId())
                .kaizenProblem(kaizenDbService.getKaizen(kaizenId).getProblem())
                .kaizenSolution(kaizenDbService.getKaizen(kaizenId).getSolution())
                .kaizenCompleted(kaizenDbService.getKaizen(kaizenId).isCompleted())
                .kaizenIsRewarded(kaizenDbService.getKaizen(kaizenId).isRewarded())
                .kaizenCompletionDate(kaizenDbService.getKaizen(kaizenId).getCompletionDate())
                .build();
        return kaizenEventDescriptionBuilder;
    }

    public UserEventDescriptionBuilder buildBasicUserDescription(int userId) {
        UserEventDescriptionBuilder userEventDescriptionBuilder1 = new UserEventDescriptionBuilder.Builder()
                .eventId(userDbService.getUser(userId).getUserId())
                .userName(userDbService.getUser(userId).getName())
                .userLastname(userDbService.getUser(userId).getLastname())
                .userBrigade(userDbService.getUser(userId).getBrigade())
                .build();
        return userEventDescriptionBuilder1;
    }
}
