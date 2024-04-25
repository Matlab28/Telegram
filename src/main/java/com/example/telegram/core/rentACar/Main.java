package com.example.telegram.core.rentACar;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//@EnableScheduling
public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi =new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot( new CarBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
