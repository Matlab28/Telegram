package com.example.telegram.core.rentACar;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class CarBot extends TelegramLongPollingBot {

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

        if (textInput.equals("/start")) {
            String msg1 = "Hi " + message.getFrom().getFirstName() + ", welcome " +
                    "to renting a car.\nRight " +
                    "now we have " +
                    "these cars:\n\nChevrolet Cobalt 2023 (Auto) - 55 AZN/Day, 380AZN/Week,\n" +
                    "Hyundai Accent 2016 (Auto) - 50 AZN/Day, 350AZN/Week,\n" +
                    "Kia Cerato 2020 (Auto) - 60AZN/Day, 420AZN/Week.\n\n" +
                    "Please choose one of them.";
            response.setText(msg1);
        }

        if (textInput.toLowerCase().contains("chevrolet")
                || textInput.toLowerCase().contains("cobalt")
                || textInput.toLowerCase().contains("hyundai")
                || textInput.toLowerCase().contains("accent")
                || textInput.toLowerCase().contains("kia")
                || textInput.toLowerCase().contains("cerato")) {

            getCard(textInput, chatId, message);
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void getCard(String textInput, Long chatId, Message message) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());
        StringBuilder builder = new StringBuilder();
        String numbers = "-?\\d+(\\.\\d+)?";

        String cardNumber = "Please for payment, enter your card number:";
        response.setText(cardNumber);

        if (textInput.length() != 16 || !textInput.matches(numbers)) {
            String cardMsg = "Invalid card number, please enter correct one...";
            response.setText(cardMsg);
        } else {
            for (int i = 0; i < textInput.length(); i += 4) {
                int endIndex = Math.min(i + 4, textInput.length());
                String substring = textInput.substring(i, endIndex);
                builder.append(substring);

                if (endIndex < textInput.length()) {
                    builder.append(" ");
                }
            }
        }

        String cardExpireDate = "Now please enter expire date:";
        response.setText(cardExpireDate);

        if (textInput.length() != 4 || !textInput.matches(numbers)) {
            String expMsg = "Invalid expire date";
            response.setText(expMsg);
        } else {
            String grateful = "Thank you for adding expire date!";
            response.setText(grateful);
        }

        String cardCvc = "Last one, please enter card CVC number:";
        response.setText(cardCvc);

        if (textInput.length() != 3 || !textInput.matches(numbers)) {
            String cvcMsg = "Please enter valid CVC number...";
            response.setText(cvcMsg);
        } else {

            String result = message.getFrom().getFirstName() + "'s card number: " + builder +
                    "\nExpire date: " + 12 + "\nCVC: " + 123;
            response.setText(result);
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
