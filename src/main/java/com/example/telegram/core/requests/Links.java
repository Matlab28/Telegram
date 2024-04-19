package com.example.telegram.core.requests;

public enum Links {
    MUSIC_1("https://music.apple.com/us/browse"),
    MUSIC_2("https://open.spotify.com/"),
    MUSIC_3("https://www.deezer.com/en/offers"),
    MUSIC_4("https://soundcloud.com/"),
    MUSIC_5("https://music.youtube.com/"),
    ELECTRONICS_1("https://maxi.az/"),
    ELECTRONICS_2("https://kontakt.az"),
    ELECTRONICS_3("https://irshad.az/"),
    ELECTRONICS_4("https://www.bakuelectronics.az/"),

    CAR_1("https://turbo.az/"),
    CAR_2("https://www.copart.com/"),
    RECIPE_1("https://www.simplyrecipes.com/"),
    RECIPE_2("https://www.allrecipes.com/");

    public String values;

    Links(String values) {
        this.values = values;
    }
}
