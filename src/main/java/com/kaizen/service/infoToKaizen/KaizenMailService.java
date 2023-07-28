package com.kaizen.service.infoToKaizen;

import com.kaizen.domain.Kaizen;
import com.kaizen.mailService.Mail;
import com.kaizen.mailService.SimpleEmailService;
import com.kaizen.service.dbService.KaizenDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KaizenMailService {

    public static String SUBJECT = "New kaizen was registered";
    private final KaizenDbService kaizenDbService;
    private final SimpleEmailService simpleEmailService;

    public void addKaizen(final Kaizen kaizen) {
        kaizenDbService.saveKaizen(kaizen);
        simpleEmailService.send(new Mail(
                "gawlas05@gmail.com",
                SUBJECT,
                """
                        New kaizen was added to database
                        Problem:\040""" + kaizen.getProblem() +
                        "\nSolution:\040" + kaizen.getSolution()
        ));
    }
}
