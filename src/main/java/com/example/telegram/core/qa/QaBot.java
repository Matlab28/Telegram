package com.example.telegram.core.qa;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Random;

@Slf4j
@Setter
@Getter
public class QaBot extends TelegramLongPollingBot {
    private Random random;

    public QaBot() {
        random = new Random();
    }

    @Override
    public String getBotUsername() {
        return "playUnoGame_bot";
    }

    @Override
    public String getBotToken() {
        return "6992258896:AAECi4glCoEwtXjHGT5dK9UCx-uR-pgNtDk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String textInput = message.getText();
                processTextInput(textInput, message.getChatId(), message);
                log.info(update.getMessage().getFrom().getFirstName());
            }
        }
    }

    private void processTextInput(String textInput, Long chatId, Message message) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());

    }
}
