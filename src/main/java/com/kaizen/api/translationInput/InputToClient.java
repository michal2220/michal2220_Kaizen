package com.kaizen.api.translationInput;

import lombok.AllArgsConstructor;
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
