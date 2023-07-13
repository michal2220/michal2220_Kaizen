package com.kaizen;

import com.kaizen.api.Translator;
import com.kaizen.api.client.TranslatorClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class TranslatorTest {

    @Autowired
    private TranslatorClient translatorClient;

    @Autowired
    private Translator translator;
    //commented as only 500 uses of API is for free
/*    @Test
    public void doTranslateTest() {
        //Given
        String input = "Zobaczymy czy działa";

        //When
        String testTranslation = translatorClient.sendTranslationToApi(input);
        System.out.println(testTranslation);

        //Then
        assertEquals("Test","We'll see if it works", testTranslation);
    }*/

    @Test
    public void doTranslateTest() {
        //Given
        String input = "Zobaczymy czy działa";

        //When
        String testTranslation = translator.doTranslate(input);
        System.out.println(testTranslation);

        //Then
        assertEquals("Test", "We'll see if it works", testTranslation);
    }
}
