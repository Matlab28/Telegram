package com.example.telegram.core.shopBot;

public enum Shops {
    SHOP_1("https://maxi.az/"),
    SHOP_2("https://kontakt.az"),
    SHOP_3("https://irshad.az/"),
    SHOP_4("https://www.bakuelectronics.az/"),

    CAR_1("https://turbo.az/"),
    CAR_2("https://www.copart.com/");

    public String values;

    Shops(String values) {
        this.values = values;
    }
}
