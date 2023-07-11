package com.kaizen.api.client;

import com.kaizen.api.translationInput.InputToClient;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TranslatorClient {

    private final InputToClient inputToClient;

    public void translationStart() {


        String inputToTranslate = inputToClient.buildInput();

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
            JSONArray translations = json.getJSONObject("data").getJSONArray("translations");
            String translatedText = translations.getJSONObject(0).getString("translatedText");

            System.out.println(translatedText);

        } catch (Exception e) {

        }
    }
}
