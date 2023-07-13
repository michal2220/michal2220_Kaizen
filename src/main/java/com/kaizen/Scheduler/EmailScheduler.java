package com.kaizen.Scheduler;

import com.kaizen.api.DadJoke;
import com.kaizen.mailService.Mail;
import com.kaizen.mailService.SimpleEmailService;
import com.kaizen.service.repository.KaizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final KaizenRepository kaizenRepository;
    private final SimpleEmailService simpleEmailService;
    private final DadJoke dadJoke;

    private static final String SUBJECT = "Weekly kaizen report";

    @Scheduled(cron = "0 0 0 ? * MON")
    public void sendKaizenStatusMail() {

        simpleEmailService.send(
                new Mail(
                        "gawlas05@gmail.com",
                        SUBJECT,
                        "Currently there are " +
                                kaizenRepository.findKaizenByCompleted(false)
                        + "unfinished ideas" + """
                                
                                
                                
                                
                                """+dadJoke.getJoke()
                )
        );
    }
}
