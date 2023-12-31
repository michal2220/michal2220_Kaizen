package com.kaizen.api.client;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DadJokesClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DadJokesClient.class);

    public String jokeALittleBit() {
        String joke = "";
        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://dad-jokes.p.rapidapi.com/random/joke")
                    .get()
                    .addHeader("X-RapidAPI-Key", "f490d96db1msh7178e6ac2d30cb1p140390jsnc9329c03e922")
                    .addHeader("X-RapidAPI-Host", "dad-jokes.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();

            joke = response.body().string();
        } catch (Exception e) {
            LOGGER.error("DadJoke could not be generated due to error: " + e);
        }
        return joke;
    }
}
