//package com.example.telegram.core.uno;
//
//import lombok.extern.slf4j.Slf4j;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.Random;
//
//
//@Slf4j
//public class UnoBot extends TelegramLongPollingBot {
//    Random random;
//
//    public UnoBot() {
//        random = new Random();
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "playUnoGame_bot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6992258896:AAECi4glCoEwtXjHGT5dK9UCx-uR-pgNtDk";
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            Message message = update.getMessage();
//            if (message.hasText()) {
//                String textInput = message.getText();
//                processTextInput(textInput, message.getChatId(), message);
//                log.info(update.getMessage().getFrom().getFirstName());
//            }
//        }
//    }
//
//    private void processTextInput(String textInput, Long chatId, Message message) {
//        SendMessage response = new SendMessage();
//        response.setChatId(chatId.toString());
//        int loop = 1;
//        StringBuilder s = new StringBuilder();
//
//        if (textInput.equals("/start")) {
//            response.setChatId(chatId.toString());
//            String msg = "Welcome " + message.getFrom().getFirstName() + ", to UNO game!";
//            response.setText(msg);
//        }
//
//
//        try {
//            int user = Integer.parseInt(textInput);
//
//            if (user == 2) {
//                for (int i = 0; i < 2; i++) {
//                    StringBuilder append = s.append((loop++)).append(") ").append(getRandomCards()).append("\n");
//                    response.setText(append.toString());
//                }
//            } else if (user == 4) {
//                for (int i = 0; i < 4; i++) {
//                    StringBuilder append = s.append((loop++)).append(") ").append(getRandomCards()).append("\n");
//                    response.setText(append.toString());
//                }
//            } else {
//                String msg = "Only 2 or 4 players can play...";
//                response.setText(msg);
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            execute(response);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String getRandomCards() {
//        int numbers = random.nextInt(10);
//        int colors = random.nextInt(4);
//        StringBuilder refund = new StringBuilder();
//        int count = 1;
//        String addColor = "";
//        String space = ", ";
//
//        for (int i = 0; i < 4; i++) {
//            addColor = switch (colors) {
//                case 0 -> UnoCards.RED.getValue();
//                case 1 -> UnoCards.BLUE.getValue();
//                case 2 -> UnoCards.GREEN.getValue();
//                case 3 -> UnoCards.YELLOW.getValue();
//                default -> "Invalid output...";
//            };
//        }
//
//        for (int i = 0; i < 7; i++) {
//            int cards = random.nextInt(9);
//            refund.append(switch (cards) {
//                case 0 -> (count++) + ". " + UnoCards.RED.getValue() + " " + numbers + space + "\n";
//                case 1 -> (count++) + ". " + UnoCards.GREEN.getValue() + " " + numbers + space + "\n";
//                case 2 -> (count++) + ". " + UnoCards.YELLOW.getValue() + " " + numbers + space + "\n";
//                case 3 -> (count++) + ". " + UnoCards.BLUE.getValue() + " " + numbers + space + "\n";
//                case 4 -> (count++) + ". " + UnoCards.BLOCK.getValue() + " " + addColor + space + "\n";
//                case 5 -> (count++) + ". " + UnoCards.FOUR_PLUS.getValue() + space + "\n";
//                case 6 -> (count++) + ". " + UnoCards.TWO_PLUS.getValue() + " " + addColor + space + "\n";
//                case 7 -> (count++) + ". " + UnoCards.REVERSED.getValue() + " " + addColor + space + "\n";
//                case 8 -> (count++) + ". " + UnoCards.CHANGE_COLOR.getValue() + space + "\n";
//                default -> throw new IllegalStateException("Invalid output...");
//            });
//        }
//        return refund.toString();
//    }
//}
