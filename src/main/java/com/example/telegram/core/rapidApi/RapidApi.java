package com.example.telegram.core.rapidApi;

import lombok.Getter;

@Getter
public enum RapidApi {
    RAPID_API_KEY("YOUR_RAPID_API_KEY");

    private String link;

    RapidApi(String link) {
        this.link = link;
    }
}
