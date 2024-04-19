package com.example.telegram.core.guessNumber;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Slf4j
public class Guessing extends TelegramLongPollingBot {
    Random random;

    public Guessing() {
        random = new Random();
    }

    @Override
    public String getBotUsername() {
        return "guesssNumber_bot";
    }

    @Override
    public String getBotToken() {
        return "7018040278:AAGM69dNsb7jTpyT2YkQZDIAcPsBRS_NXKY";
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
        int numbers = random.nextInt(1, 20);
        String alphabet = ".*[A-Za-z].*";

        if (textInput.equals("/start")) {
            String msg = "Hi " + message.getFrom().getFirstName() +
                    ", welcome to 'Number Guessing' game." +
                    "\nThere'll be random numbers between 1-20. Just try to find it!\nGood Luck! 🍀";
            response.setText(msg);
        } else if (textInput.matches(alphabet)) {
            String msg = "Please enter only numbers...";
            response.setText(msg);
        }

        String msg = "Your guess '" + textInput + "', mine is '" + numbers + "'.\n";

        try {
            int guess = Integer.parseInt(textInput);

            if (guess > 20) {
                String answer = "Please try to find numbers only between 1-20...";
                response.setText(answer);
            } else if (guess == numbers) {
                String answer = msg + "Congratulations! You found right the random number!";
                response.setText(answer);
            } else {
                String answer = msg + "Unfortunately you guessed wrong...next time...";
                response.setText(answer);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
