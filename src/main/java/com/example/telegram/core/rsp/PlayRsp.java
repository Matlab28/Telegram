package com.example.telegram.core.rsp;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Slf4j
public class PlayRsp extends TelegramLongPollingBot {
    Random random;

    public PlayRsp() {
        random = new Random();
    }

    @Override
    public String getBotUsername() {
        return "rsppGamee_bot";
    }

    @Override
    public String getBotToken() {
        return "7027597111: -O5FtMf2LEOw2KzT0JDhf9nE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        int computer = random.nextInt(3);
        SendMessage response = new SendMessage();
        String answer;

        String command = update.getMessage().getText();
        log.info(update.getMessage().getFrom().getFirstName());
        String rsp = switch (computer) {
            case 0 -> Tools.PAPER.values;
            case 1 -> Tools.ROCK.values;
            case 2 -> Tools.SCISSORS.values;
            default -> "Invalid output...";
        };

        switch (command) {
            case "/start" -> {
                response.setChatId(update.getMessage().getChatId().toString());
                String message = "Hi " + update.getMessage().getFrom().getFirstName() +
                        ", welcome to Rock-paper-Scissors game!\nPlease select one of the commands for playing....";

                response.setText(message);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            case "/rock" -> {
                response.setChatId(update.getMessage().getChatId().toString());
                String message = "You choice Rock, mine is " + rsp + ".\n";
                answer = switch (computer) {
                    case 0 -> message + "You lost, I won...";
                    case 1 -> message + "Wow, game is tie!";
                    case 2 -> message + "Congratulations! You won!";
                    default -> "Invalid input...";
                };
                response.setText(answer);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            case "/paper" -> {
                response.setChatId(update.getMessage().getChatId().toString());
                String message = "You choice Paper, mine is " + rsp + ".\n";
                answer = switch (computer) {
                    case 0 -> message + "Wow, game is tie!";
                    case 1 -> message + "Congratulations! You won!";
                    case 2 -> message + "You lost, I won...";
                    default -> "Invalid input...";
                };
                response.setText(answer);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            case "/scissors" -> {
                response.setChatId(update.getMessage().getChatId().toString());
                String message = "You choice Scissors, mine is " + rsp + ".\n";
                answer = switch (computer) {
                    case 0 -> message + "Congratulations! You won!";
                    case 1 -> message + "You lost, I won...";
                    case 2 -> message + "Wow, game is tie!";
                    default -> "Invalid input...";
                };
                response.setText(answer);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
