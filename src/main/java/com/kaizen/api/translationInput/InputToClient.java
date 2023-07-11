package com.kaizen.api.translationInput;

import com.kaizen.service.dbService.KaizenDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class InputToClient {

    private final KaizenDbService kaizenDbService;

    public String buildInput() {

        String problem = kaizenDbService.getKaizen(1).getProblem();
        System.out.println(problem);
        return  "{" +
                "\"q\": \"" + problem + "\",\r" +
                "\"source\": \"pl\",\r" +
                "\"target\": \"en\",\r" +
                "\"format\": \"text\"\r" +
                "}";
    }
}
