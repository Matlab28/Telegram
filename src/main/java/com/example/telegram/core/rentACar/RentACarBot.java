package com.example.telegram.core.rentACar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
public class RentACarBot extends TelegramLongPollingBot {

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
        String clarification = "Good choice! Please add Pick up date (i.e. 21 April)";
        String days = "How many days will you rent it?";

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
            response.setText(clarification);
            if (!response.getText().isEmpty()) {
                response.setText(days);
                try {
                    int time = Integer.parseInt(textInput);

                    if (!response.getText().toLowerCase().matches(numbers)) {
                        String warning = "Please enter only numbers (i.e. 4)...";
                        response.setText(warning);
                    }


                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Value("0 0 11 25 4 ?")
    private String cronExpression;
    @Scheduled(cron = "0 0 11 25 4 ?")
    public void scheduledReminder() {

    }

    private String setTime() {
        SendMessage response = new SendMessage();
        try {
            String textInput = " ";
            Long chatId = null;

            int date = Integer.parseInt(textInput);

            if (LocalDateTime.now().equals(date)) {
                response.setChatId(chatId.toString());
                response.setText("Unfortunately right now unavailable to take a car. Choose another time.");
            }

            response.setText(cronExpression);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @Scheduled(cron = "0 0 11 4  4 ?")
    private void pickup() {
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

    @Scheduled(cron = "0 0 11 3 5 ?")
    private void dropOff() {
        Message message = new Message();
        SendMessage response = new SendMessage();
        String text = "Good morning " + message.getFrom().getFirstName() + ", today is the drop off day." +
                " Please return the car today by 8 PM. Have a good day 😊.";
        response.setText(text);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
