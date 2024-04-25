package com.example.telegram.chatBot.bot;

import com.example.telegram.chatBot.bot.Bot;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class Main {
    public static void main(String[] args) {
        new Bot();
    }
}
