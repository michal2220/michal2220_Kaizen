package com.kaizen.api.client;

import com.kaizen.api.translationInput.InputToClient;
import lombok.RequiredArgsConstructor;
import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TranslatorClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorClient.class);

    private final InputToClient inputToClient;

    public String doTranslate(String input) {
        JSONArray translations = new JSONArray();

        LOGGER.info("**** Preparing string to translate ****");

        String inputToTranslate = "{" +
                "\"q\": \"" + input + "\",\r" +
                "\"source\": \"pl\",\r" +
                "\"target\": \"en\",\r" +
                "\"format\": \"text\"\r" +
                "}";

        LOGGER.info("**** Sending translation ****");

        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, inputToTranslate);
            Request request = new Request.Builder()
                    .url("https://google-translator9.p.rapidapi.com/v2")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("X-RapidAPI-Key", "728922b712msh829ff71372dbe89p1471fcjsn90aeca9b6d17")
                    .addHeader("X-RapidAPI-Host", "google-translator9.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();


            String text = response.body().string();

            JSONObject json = new JSONObject(text);
            translations = json.getJSONObject("data").getJSONArray("translations");

            LOGGER.info("**** Translation completed ****");

        } catch (Exception e) {

        }
        return translations.getJSONObject(0).getString("translatedText");
    }
}
