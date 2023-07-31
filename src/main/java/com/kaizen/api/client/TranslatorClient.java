package com.kaizen.api.client;

import com.kaizen.api.translationInput.InputToClient;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TranslatorClient {
    @Value("${api.translator.endpoint.url}")
    private String translatorApiEndpoint;
    @Value("${api.translator.endpoint.key}")
    private String apiKey;
    @Value("${api.translator.endpoint.host}")
    private String apiHost;
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorClient.class);
    private final InputToClient inputToClient;


    public String sendTranslationToApi(String input) {
        String translatedInput = "";
        LOGGER.info("**** Preparing string to translate ****");
        LOGGER.info("**** Sending translation ****");

        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, inputToClient.buildInput(input));
            Request request = new Request.Builder()
                    .url(translatorApiEndpoint)
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("X-RapidAPI-Key", apiKey)
                    .addHeader("X-RapidAPI-Host", apiHost)
                    .build();

            Response response = client.newCall(request).execute();
            translatedInput = response.body().string();

        } catch (Exception e) {
            LOGGER.error("Translation could not happen due to error: " + e);
        }
        return translatedInput;
    }
}
