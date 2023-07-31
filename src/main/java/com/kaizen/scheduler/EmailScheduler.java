package com.kaizen.scheduler;

import com.kaizen.api.DadJoke;
import com.kaizen.api.Translator;
import com.kaizen.mailService.Mail;
import com.kaizen.mailService.SimpleEmailService;
import com.kaizen.service.repository.KaizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final KaizenRepository kaizenRepository;
    private final SimpleEmailService simpleEmailService;
    private final DadJoke dadJoke;
    private final Translator translator;

    private static final String SUBJECT = "Weekly kaizen report";

    @Scheduled(cron = "0 0 0 ? * MON")
    public void sendKaizenStatusMail() {
        String joke = dadJoke.getJoke();

        simpleEmailService.send(
                new Mail(
                        "gawlas05@gmail.com",
                        SUBJECT,
                        "Currently there are " +
                                kaizenRepository.findKaizenByCompleted(false).size()
                                + " unfinished ideas" + """
                                   
                                   
                                   
                                                             
                                Joke for today:   
                                   """ + joke + """
                                                                
                                                                
                                Po Polsku:                      
                                """ + translator.doTranslate(joke)
                )
        );
    }
}
