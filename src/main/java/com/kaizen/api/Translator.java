package com.kaizen.api;

import com.kaizen.api.client.TranslatorClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Translator {
    private final TranslatorClient translatorClient;

    public String doTranslate(String input) {
        JSONArray translations = new JSONArray();
        JSONObject json = new JSONObject(translatorClient.sendTranslationToApi(input));
        translations = json.getJSONObject("data").getJSONArray("translations");
        return translations.getJSONObject(0).getString("translatedText");
    }
}
