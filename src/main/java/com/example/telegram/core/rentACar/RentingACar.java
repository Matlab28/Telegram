package com.example.telegram.core.rentACar;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Setter
@Getter
public class RentingACar extends TelegramLongPollingBot {
    private String cronExpression;

    private Map<String, String> createMonthCronExpressions(String textInput) {
        Map<String, String> expressions = new HashMap<>();
//        expressions.put("january", "0 0 0 1 * ?");
        if (textInput.toLowerCase().contains("april")) {
            expressions.put("April", "0 0 11 24 4 * ?");
        } else if (textInput.toLowerCase().contains("may")) {
            expressions.put("May", "0 0 11 2 5 * ?");
        }
        return expressions;
    }

    @Override
    public String getBotUsername() {
        return "yourMusicSongs_bot";
    }

    @Override
    public String getBotToken() {
        return "6627982719:AAHlTAMMWAJmUr-8rDe4N_z0whUsloFdiwk";
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
        String numbers = "-?\\d+(\\.\\d+)?";
        String alphabet = ".*[A-Za-z].*";
        String pickup = "Good choice! Please add Pick up date (i.e. 21 April)\n\n";
        String dropOff = "How many days will you rent it?";


        if (textInput.equals("/start")) {
            String msg = "Hi " + message.getFrom().getFirstName() + ", welcome " +
                    "to renting a car.\nRight " +
                    "now we have " +
                    "these cars:\n\nChevrolet Cobalt 2023 (Auto) - 55 AZN/Day, 380AZN/Week,\n" +
                    "Hyundai Accent 2016 (Auto) - 50 AZN/Day, 350AZN/Week,\n" +
                    "Kia Cerato 2020 (Auto) - 60AZN/Day, 420AZN/Week.\n\n" +
                    "Please choose one of them.";
            response.setText(msg);
        }

        if (textInput.toLowerCase().contains("chevrolet")
                || textInput.toLowerCase().contains("cobalt")) {
            response.setText(pickup);
            if (!response.getText().isEmpty()) {
                createMonthCronExpressions(textInput);
            }

        }
    }

    @Scheduled(cron = "0 0 11 24 4 ?")
    public void reminder() {
        Message message = new Message();
        SendMessage response = new SendMessage();
        String text = "Good morning " + message.getFrom().getFirstName() + ", your car is ready." +
                " You can take it. Have a good ride 😊.";
        response.setText(text);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
