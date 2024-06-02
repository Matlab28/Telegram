package com.example.telegram.core.rentACar;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class Rent extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "YOUR_BOT_NAME";
    }

    @Override
    public String getBotToken() {
        return "YOUR_BOT_TOKEN";
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
        StringBuilder builder = new StringBuilder();
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());
        String numbers = "-?\\d+(\\.\\d+)?";
        String alphabet = ".*[A-Za-z].*";
        String pickup = "Good choice! Please add pickup date.\n\n";
        String dropOff = "How many days will you rent it?";

        String msg = "By the way, unfortunately right now only from 25 April till " +
                "2 May is available, if it's alright for for you, please confirm it " +
                "(you can write yes, okay, or alright).";

        if (textInput.equals("/start")) {
            String msg1 = "Hi " + message.getFrom().getFirstName() + ", welcome " +
                    "to renting a car.\nRight " +
                    "now we have " +
                    "these cars:\n\nChevrolet Cobalt 2023 (Auto) - 55 AZN/Day, 380AZN/Week,\n" +
                    "Hyundai Accent 2016 (Auto) - 50 AZN/Day, 350AZN/Week,\n" +
                    "Kia Cerato 2020 (Auto) - 60AZN/Day, 420AZN/Week.\n\n" +
                    "Please choose one of them.";
            response.setText(msg1);
        } else if (textInput.toLowerCase().contains("chevrolet")
                || textInput.toLowerCase().contains("cobalt")
                || textInput.toLowerCase().contains("hyundai")
                || textInput.toLowerCase().contains("accent")
                || textInput.toLowerCase().contains("kia")
                || textInput.toLowerCase().contains("cerato")) {
//            response.setText(pickup + msg);

//        if (textInput.toLowerCase().contains("yes")
//                || textInput.toLowerCase().contains("okay")
//                || textInput.toLowerCase().contains("alright")) {
//            String confirmation = "Great! please enter your card " +
//                    "values(card number, expire date, and CVC).\n";
//            response.setText(confirmation);
//        }

//            String c = "First enter card number:";
//            response.setText(c);
//        String textinggg = response.getText();

            String enter = "Card number...";
            String text = response.getText();

            if (text.length() != 16 && !text.toLowerCase().matches(numbers)) {
                String cardMsg = "Invalid card number, please enter correct one...";
                response.setText(cardMsg);

                for (int i = 0; i < text.length(); i += 4) {
                    int endIndex = Math.min(i + 4, text.length());
                    String substring = text.substring(i, endIndex);
                    builder.append(substring);

                    if (endIndex < text.length()) {
                        builder.append(" ");
                    }
                }

//            try {
//                execute(response);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
            }

            String expDate = "Now please enter expire date:";
            response.setText(expDate);

            if (textInput.length() != 4
                    && !textInput.toLowerCase().matches(numbers)
                    && textInput.toLowerCase().matches(alphabet)) {
                String expMsg = "Please enter correct expire date";
                response.setText(expMsg);
//            try {
//                execute(response);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
            }

            String expire = response.getText();

            String cvc = "Last one please enter CVC code:";
            response.setText(cvc);

            if (textInput.length() != 3
                    && !textInput.toLowerCase().matches(numbers)
                    && textInput.toLowerCase().matches(alphabet)) {
                String cvcMsg = "Please enter valid CVC number...";
                response.setText(cvcMsg);
//            try {
//                execute(response);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
            }

            String cvcNum = response.getText();

            String result = message.getFrom().getFirstName() + "'s card number: " + builder +
                    "\nExpire date: " + expire + "\nCVC: " + cvcNum;
            response.setText(result);
            response.setText(enter);

        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}


//You can take your car 25th April any time ☺️!
