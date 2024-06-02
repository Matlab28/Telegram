package com.example.telegram.core.travel;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TravelingBot extends TelegramLongPollingBot {

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

        if (textInput.equals("/start")) {
            String msg = "Hello " + message.getFrom().getFirstName() + ", " +
                    "I hope you'll have a very good trip.\nPlease ask whatever " +
                    "you need (tickets, hotel, airbnb etc.).";
            response.setText(msg);
        }

        if (textInput.toLowerCase().contains("hotel")
                || textInput.toLowerCase().contains("place")) {
            String msg = "Of course, you can check '" + Links.BOOKING.getValue() + "' or '" + Links.AIRBNB.getValue() +
                    "'. You can see the feedbacks and you can whichever you want!";
            response.setChatId(chatId.toString());
            response.setText(msg);
        } else if (textInput.toLowerCase().contains("ticket")
                || textInput.toLowerCase().contains("plane")
                || textInput.toLowerCase().contains("flight")) {
            String msg = "You can check '" + Links.TICKET_1.getValue() + "' or '" + Links.TICKET_2.getValue() +
                    "' both sites, and you can make a decision which one is cheaper and more affordable.";
            response.setChatId(chatId.toString());
            response.setText(msg);
        } else if (textInput.toLowerCase().contains("tripadvisor")
                || textInput.toLowerCase().contains("rate")
                || textInput.toLowerCase().contains("rating")) {
            String msg = "Going before a place or restaurant it's better to check its rate. " +
                    "You can check from Tripadvisor. Here is:\n" + Links.TRIPADVISOR.getValue();
            response.setChatId(chatId.toString());
            response.setText(msg);
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
