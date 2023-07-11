package com.kaizen;

import com.kaizen.api.client.TranslatorClient;
import com.kaizen.api.translationInput.InputToClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TranslatorTest {

    @Autowired
    private InputToClient inputToClient;

    @Autowired
    private TranslatorClient translatorClient;

    @Test
    public void testttt() throws ParseException {
        translatorClient.translationStart();
    }

}
