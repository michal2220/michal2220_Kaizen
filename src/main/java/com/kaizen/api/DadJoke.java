package com.kaizen.api;

import com.kaizen.api.client.DadJokesClient;
import com.kaizen.api.client.TranslatorClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class DadJoke {

    private final DadJokesClient dadJokesClient;
    JSONArray body;
    JSONObject objectInBody;
    public String getJoke() {
        JSONObject jsonObject = new JSONObject(dadJokesClient.jokeALittleBit());
        body = jsonObject.getJSONArray("body");
        objectInBody = body.getJSONObject(0);

        return "Joke for today: " +
                "\n" + objectInBody.getString("setup") +
                "\n" + objectInBody.getString("punchline");
    }
}
