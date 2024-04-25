package com.example.telegram.core.music;

import lombok.Getter;

@Getter
public enum ApiLinks {
    RAPID_API_KEY("674678e122mshd00ec5b8f945302p1052bcjsn0ad69ed2af91");

    private String value;
    ApiLinks(String value) {
        this.value = value;
    }
}
