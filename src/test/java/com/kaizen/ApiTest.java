package com.kaizen;

import com.kaizen.api.DadJoke;
import com.kaizen.api.Translator;
import com.kaizen.api.client.DadJokesClient;
import com.kaizen.api.client.TranslatorClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ApiTest {

    @Autowired
    private TranslatorClient translatorClient;

    @Autowired
    private Translator translator;

    @Autowired
    private DadJoke dadJoke;

    //commented as only 500 uses of API is for free
/*    @Test
    public void doTranslateTest() {
        //Given
        String input = "Zobaczymy czy działa";

        //When
        String testTranslation = translator.doTranslate(input);
        System.out.println(testTranslation);

        //Then
        assertEquals("Test", "We'll see if it works", testTranslation);
    }*/


    @Test
    public void dadJokeTest() {
        //Given + When
        String test = dadJoke.getJoke();

        //Then
        assertNotNull(test);
    }


}
