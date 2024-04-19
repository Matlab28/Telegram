package com.example.telegram.core.travel;

import lombok.Getter;

@Getter
public enum Links {
    TICKET_1("https://www.aviasales.az/"),
    TICKET_2("https://www.esky.eu/"),
    BOOKING("https://www.booking.com/"),
    AIRBNB("https://www.airbnb.com/"),
    TRIPADVISOR("https://www.tripadvisor.com/");

    private final String value;

    Links(String value) {
        this.value = value;
    }
}
