package com.example.telegram.core.rentACar;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class Renting extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "YOUR_BOT_USERNAME";
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
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());
        StringBuilder builder =new StringBuilder();
        String numbers = "-?\\d+(\\.\\d+)?";
        String alphabet = ".*[A-Za-z].*";

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

            String enter = "For payment, please enter card number...";
            response.setText(enter);
        }

        else if (textInput.matches(numbers) && textInput.length() != 16) {
            String cardMsg = "Invalid card number, please enter correct one (16 digits)...";
            response.setText(cardMsg);
//            for (int i = 0; i < textInput.length(); i += 4) {
//                int endIndex = Math.min(i + 4, textInput.length());
//                String substring = textInput.substring(i, endIndex);
//                builder.append(substring);
//
//                if (endIndex < textInput.length()) {
//                    builder.append(" ");
//                }
//            }
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

            String expDate = "Now please enter expire date:";
            response.setText(expDate);
            String expire = response.getText();

            if (textInput.length() != 4
                    && !textInput.toLowerCase().matches(numbers)
                    && textInput.toLowerCase().matches(alphabet)) {
                String expMsg = "Please enter correct expire date";
                response.setText(expMsg);
            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            } else {
                String cvc = "Last one please enter CVC code:";
                response.setText(cvc);


            }

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
        } else {
            String cvcNum = response.getText();

            String result = message.getFrom().getFirstName() + "'s card number: " + builder +
                    "\nExpire date: " + expire + "\nCVC: " + cvcNum;
            response.setText(result);
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error("Error sending message: {}", e.getMessage());
        }
    }
}

