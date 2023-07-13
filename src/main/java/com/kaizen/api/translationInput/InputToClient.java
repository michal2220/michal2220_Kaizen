package com.kaizen.api.translationInput;

import com.kaizen.service.dbService.KaizenDbService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class InputToClient {

    public String buildInput(String input) {

        return  "{" +
                "\"q\": \"" + input + "\",\r" +
                "\"source\": \"pl\",\r" +
                "\"target\": \"en\",\r" +
                "\"format\": \"text\"\r" +
                "}";
    }
}
