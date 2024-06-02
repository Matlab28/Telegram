package com.example.telegram.core.requests;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Random;

@Slf4j
public class RequestBot extends TelegramLongPollingBot {
    Random random;

    public RequestBot() {
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
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String textInput = message.getText();
                processTextInput(textInput, message.getChatId(), message);
                log.info(update.getMessage().getFrom().getFirstName());
            }
        }
    }

    private void processTextInput(String textInput, Long chatId, Message messaging) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());
        int music = random.nextInt(5);
        int electronics = random.nextInt(4);
        int cars = random.nextInt(2);
        String msg = "Great choice! Listening music reduces stress. You can check this:\n";
        String msg2 = "Yeah, sometimes we need to change or replace some old stuff. You can check it:\n";
        String msg3 = "If you need new car, you can check it!\n";
        String msg4 = "Hmmm, cooking food...😋..good choice! You can check it:\n";

        String musicRec = switch (music) {
            case 0 -> msg + Links.MUSIC_1.values;
            case 1 -> msg + Links.MUSIC_2.values;
            case 2 -> msg + Links.MUSIC_3.values;
            case 3 -> msg + Links.MUSIC_4.values;
            case 4 -> msg + Links.MUSIC_5.values;
            default -> "Invalid output...";
        };

        String electRec = switch (electronics) {
            case 0 -> msg2 + Links.ELECTRONICS_1.values;
            case 1 -> msg2 + Links.ELECTRONICS_2.values;
            case 2 -> msg2 + Links.ELECTRONICS_3.values;
            case 3 -> msg2 + Links.ELECTRONICS_4.values;
            default -> "Invalid output...";
        };

        String carRec = switch (cars) {
            case 0 -> msg3 + Links.CAR_1.values;
            case 1 -> msg3 + Links.CAR_2.values;
            default -> "Invalid output...";
        };

        String foodRec = switch (cars) {
            case 0 -> msg4 + Links.RECIPE_1.values;
            case 1 -> msg4 + Links.RECIPE_2.values;
            default -> "Invalid output...";
        };

        if (textInput.equals("/start")) {
            String mess = "Hi " + messaging.getFrom().getFirstName() +
                    ", welcome your wish list!\nIf you need something special, " +
                    "it might be music sites, electronics, car sites, etc. be free for asking 😊!";
            response.setText(mess);
        }

        if (textInput.toLowerCase().contains("music") || textInput.toLowerCase().contains("song")) {
            response.setChatId(chatId.toString());
            response.setText(musicRec);
        } else if (textInput.toLowerCase().contains("electronics")) {
            response.setChatId(chatId.toString());
            response.setText(electRec);
        } else if (textInput.toLowerCase().contains("car")) {
            response.setChatId(chatId.toString());
            response.setText(carRec);
        } else if (textInput.toLowerCase().contains("food") || textInput.toLowerCase().contains("recipe")) {
            response.setChatId(chatId.toString());
            response.setText(foodRec);
        }

        try {
            execute(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
