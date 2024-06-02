package com.example.telegram.core.music;

import lombok.Getter;

@Getter
public enum ApiLinks {
    RAPID_API_KEY("YOUR_RAPID_API_KEY");

    private String value;
    ApiLinks(String value) {
        this.value = value;
    }
}
