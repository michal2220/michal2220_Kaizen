package com.kaizen;

import com.kaizen.api.client.TranslatorClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class TranslatorTest {

    @Autowired
    private TranslatorClient translatorClient;

    @Test
    public void doTranslateTest() {
        //Given
        String input = "Próba czy działa";

        //When
        String testTranslation = translatorClient.doTranslate(input);
        System.out.println(testTranslation);

        //Then
        assertEquals("Test","Try if it works", testTranslation);
    }
}
