package com.example.telegram.core.uno;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@Setter
@Getter
public class UnoBot extends TelegramLongPollingBot {
    private Random random;
    private List<String> availableCommands;
    private LinkedList<String> computerCards;
    private String lastCard;

    public UnoBot() {
        random = new Random();
        availableCommands = new ArrayList<>();
        computerCards = new LinkedList<>();
        initializeAvailableCommands();
    }

    private String initializeAvailableCommands() {
        availableCommands.add("/red");
        availableCommands.add("/green");
        availableCommands.add("/yellow");
        availableCommands.add("/blue");
        availableCommands.add("/block");
        availableCommands.add("/fourplus");
        availableCommands.add("/twoplus");
        availableCommands.add("/reversed");
        availableCommands.add("/changecolor");
        return null;
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
        if (update.hasMessage() && update.getMessage().hasText()) {
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
        String text = message.getText().trim();
        String s;

        if (text.equals("/start")) {
            sendTextMessage(chatId, "Welcome " + message.getFrom().getFirstName() + ", to UNO game!");
        } else if (text.startsWith("/getcard")) {
            sendTextMessage(chatId, getRandomCards());
        } else if (availableCommands.contains(text)) {
            if (isValidPlay(text)) {
                lastCard = text;
                availableCommands.remove(text);
                sendTextMessage(chatId, "You played " + text);
            } else {
                sendTextMessage(chatId, "You can't play this card. Please choose a valid card.");
            }
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error("Error sending message: {}", e.getMessage());
        }


        try {
            int user = Integer.parseInt(textInput);

            if (user == 2) {
                for (int i = 0; i < 2; i++) {
                    s = getRandomCards();
                    computerCards.add(s);
                }

                for (String cards : computerCards) {
                    response.setText("Cards:\n" + cards + "\n");
                    break;
                }

                if (computerCards.contains(getRandomCards())) {
                    computerCards.add(initializeAvailableCommands());
                }

                int startIndex = 8;
                int endIndex = 13;

                List<String> subList = computerCards.subList(startIndex, endIndex + 1);


                for (int i = 0; i <= subList.size(); i++) {
                    response.setText("Element at index " + (startIndex + i) + ": " + subList.get(i));
                }

            } else if (user == 4) {
                for (int i = 0; i < 4; i++) {
                    s = getRandomCards();
                    computerCards.add(s);
                }

                for (String cards : computerCards) {
                    response.setText("Cards:\n" + cards);
                    break;
                }
            } else {
                String msg = "Only 2 or 4 players can play...";
                response.setText(msg);
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

    private boolean isValidPlay(String selectedCard) {
        if (lastCard == null) {
            return true;
        } else {
            String selectedColor = selectedCard.substring(1);
            String selectedNumber = selectedCard.substring(1).equals("BLOCK") ||
                    selectedCard.equals("FOUR_PLUS") ||
                    selectedCard.equals("TWO_PLUS") ||
                    selectedCard.equals("REVERSED") ||
                    selectedCard.equals("CHANGE_COLOR") ?
                    "" : selectedCard.substring(1);

            String lastColor = lastCard.substring(1);
            String lastNumber = lastCard.substring(1).equals("BLOCK") ||
                    lastCard.equals("FOUR_PLUS") ||
                    lastCard.equals("TWO_PLUS") ||
                    lastCard.equals("REVERSED") ||
                    lastCard.equals("CHANGE_COLOR") ?
                    "" : lastCard.substring(1);

            return selectedColor.equals(lastColor) || selectedNumber.equals(lastNumber);
        }
    }

    private void sendTextMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message: {}", e.getMessage());
        }
    }

    private String getRandomCards() {
        int numbers = random.nextInt(10);
        int colors = random.nextInt(4);
        StringBuilder refund = new StringBuilder();
        int count = 1;
        String addColor = "";
        String space = ", ";


        for (int i = 0; i < 4; i++) {
            addColor = switch (colors) {
                case 0 -> UnoCards.RED.getValue();
                case 1 -> UnoCards.BLUE.getValue();
                case 2 -> UnoCards.GREEN.getValue();
                case 3 -> UnoCards.YELLOW.getValue();
                default -> "Invalid output...";
            };
        }

        for (int i = 0; i < 7; i++) {
            int cards = random.nextInt(9);
            refund.append(switch (cards) {
                case 0 -> (count++) + ". " + UnoCards.RED.getValue() + " " + numbers + space + "\n";
                case 1 -> (count++) + ". " + UnoCards.GREEN.getValue() + " " + numbers + space + "\n";
                case 2 -> (count++) + ". " + UnoCards.YELLOW.getValue() + " " + numbers + space + "\n";
                case 3 -> (count++) + ". " + UnoCards.BLUE.getValue() + " " + numbers + space + "\n";
                case 4 -> (count++) + ". " + UnoCards.BLOCK.getValue() + " " + addColor + space + "\n";
                case 5 -> (count++) + ". " + UnoCards.FOUR_PLUS.getValue() + space + "\n";
                case 6 -> (count++) + ". " + UnoCards.TWO_PLUS.getValue() + " " + addColor + space + "\n";
                case 7 -> (count++) + ". " + UnoCards.REVERSED.getValue() + " " + addColor + space + "\n";
                case 8 -> (count++) + ". " + UnoCards.CHANGE_COLOR.getValue() + space + "\n";
                default -> throw new IllegalStateException("Invalid output...");

            });
        }

        return refund.toString();
    }
}
