package com.example.telegram.core.uno;

import lombok.Getter;

@Getter
public enum UnoCards {
    RED("Red 🛑"),
    GREEN("Green 🟩"),
    YELLOW("Yellow 🟨"),
    BLUE("Blue 🟦"),
    BLOCK("Block"),
    FOUR_PLUS("4+"),
    TWO_PLUS("2+"),
    REVERSED("Reversed"),
    CHANGE_COLOR("Color change");

    private String value;

    UnoCards(String value) {
        this.value = value;
    }
}