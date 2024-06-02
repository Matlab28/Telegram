package com.example.telegram.core.discover;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Slf4j
public class LearningBot extends TelegramLongPollingBot {
    Random random;

    public LearningBot() {
        random = new Random();
    }

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
        int randomStories = random.nextInt(4);
        SendMessage response = new SendMessage();

        String command = update.getMessage().getText();
        log.info(update.getMessage().getFrom().getFirstName());
        String stories = switch (randomStories) {
            case 0 -> Stories.RESPONSE_1.values;
            case 1 -> Stories.RESPONSE_2.values;
            case 2 -> Stories.RESPONSE_3.values;
            case 3 -> Stories.RESPONSE_4.values;
            default -> "Invalid output...";
        };

        String explanations = switch (randomStories) {
            case 0 -> Explanation.RESPONSE_1.values;
            case 1 -> Explanation.RESPONSE_2.values;
            case 2 -> Explanation.RESPONSE_3.values;
            case 3 -> Explanation.RESPONSE_4.values;
            default -> "Invalid output...";
        };

        if (command.equals("/start")) {
            response.setChatId(update.getMessage().getChatId().toString());
            String message = "Hi " + update.getMessage().getFrom().getFirstName() +
                    ", welcome to learning bot!\nLet's learn and discover together!";
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (command.equals("/teach")) {
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(stories);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (command.equals("/explain")) {
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(explanations);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
