package com.example.telegram.chatBot.aiSupported;

import lombok.Getter;

@Getter
public enum Links {
    CAR_1("- https://turbo.az"),
    CAR_2("- https://www.copart.com"),
    BUILDING("- https://bina.az"),
    ELECTRONICS_1("- https://maxi.az/"),
    ELECTRONICS_2("- https://kontakt.az"),
    ELECTRONICS_3("- https://irshad.az/"),
    ELECTRONICS_4("- https://www.bakuelectronics.az/");

    private String value;

    Links(String value) {
        this.value = value;
    }
}
