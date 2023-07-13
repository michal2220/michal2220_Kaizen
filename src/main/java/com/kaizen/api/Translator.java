package com.kaizen.api;

import com.kaizen.api.client.TranslatorClient;
import com.kaizen.api.translationInput.InputToClient;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
