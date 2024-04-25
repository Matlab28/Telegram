package com.example.telegram.core.rapidApi;

import lombok.Getter;

@Getter
public enum RapidApi {
    RAPID_API_KEY("674678e122mshd00ec5b8f945302p1052bcjsn0ad69ed2af91");

    private String link;

    RapidApi(String link) {
        this.link = link;
    }
}
