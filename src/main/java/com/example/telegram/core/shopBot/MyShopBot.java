package com.example.telegram.core.shopBot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Slf4j
public class MyShopBot extends TelegramLongPollingBot {
    Random random;

    public MyShopBot() {
        random = new Random();
    }

    @Override
    public String getBotUsername() {
        return "find_electronics_bot";
    }

    @Override
    public String getBotToken() {
        return "7017841601:AAGKZjHlmHsxRw6pGpq-ssuHUP0EwzqoDTw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        int electronics = random.nextInt(4);
        int cars = random.nextInt(2);
        SendMessage response = new SendMessage();

        String command = update.getMessage().getText();
        log.info(update.getMessage().getFrom().getFirstName());
        String shops = switch (electronics) {
            case 0 -> "Here is:\n" + Shops.SHOP_1.values;
            case 1 -> "Here is:\n" + Shops.SHOP_2.values;
            case 2 -> "Here is:\n" + Shops.SHOP_3.values;
            case 3 -> "Here is:\n" + Shops.SHOP_4.values;
            default -> "Invalid output...";
        };

        String carShops = switch (cars) {
            case 0 -> "Here is:\n" + Shops.CAR_1.values;
            case 1 -> "Here is:\n" + Shops.CAR_2.values;
            default -> "Invalid output...";
        };

        switch (command) {
            case "/start" -> {
                response.setChatId(update.getMessage().getChatId().toString());
                String message = "Hi " + update.getMessage().getFrom().getFirstName() +
                        ", welcome!\nYou can find the best electronics or car websites!\nJust select one of the commands.";

                response.setText(message);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            case "/electronics" -> {
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(shops);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            case "/cars" -> {
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(carShops);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
