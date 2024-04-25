package com.example.telegram.core.rentACar;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Getter
@Setter
@Component
public class RentingBot extends TelegramLongPollingBot {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM");
    private Date pickupDate;
    private int rentalDays;
    private Update update = new Update();

    @Override
    public String getBotUsername() {
        return "yourMusicSongs_bot";
    }

    @Override
    public String getBotToken() {
        return "6627982719:AAHlTAMMWAJmUr-8rDe4N_z0whUsloFdiwk";
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
        String numbers = "-?\\d+(\\.\\d+)?";
        String clarification = "Good choice! Please add Pick up date (i.e. 21 April)";
        String days = "How many days will you rent it?";

        if (textInput.equals("/start")) {
            String msg = "Hi " + message.getFrom().getFirstName() + ", welcome " +
                    "to renting a car.\nRight " +
                    "now we have " +
                    "these cars:\n\nChevrolet Cobalt 2023 (Auto) - 55 AZN/Day, 380AZN/Week,\n" +
                    "Hyundai Accent 2016 (Auto) - 50 AZN/Day, 350AZN/Week,\n" +
                    "Kia Cerato 2020 (Auto) - 60AZN/Day, 420AZN/Week.\n\n" +
                    "Please choose one of them.";
            response.setText(msg);
        }

        if (textInput.toLowerCase().contains("chevrolet")
                || textInput.toLowerCase().contains("cobalt")) {
            response.setText(clarification);
            if (!response.getText().isEmpty()) {
                response.setText(days);

                int time = Integer.parseInt(textInput);

                if (!response.getText().matches(numbers)) {
                    String warning = "Please enter only numbers (i.e. 4)...";
                    response.setText(warning);

                    try {
                        if (pickupDate == null) {
                            pickupDate = dateFormat.parse(textInput);
                            response.setText("Pick-up date set to " + dateFormat.format(pickupDate) + ". Please enter the number of rental days.");
                        } else if (rentalDays == 0) {
                            rentalDays = Integer.parseInt(textInput);
                            response.setText("Rental period set to " + rentalDays + " days. Thank you for providing the information.");
                        }
                    } catch (ParseException | NumberFormatException e) {
                        response.setText("Invalid input format. Please use the format: dd MMM for the date, and provide the number of rental days as a numeric value.");
                    }
                }
            }
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 11 * * ?")
    public void sendPickupNotification() {
        if (pickupDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(pickupDate);
            cal.add(Calendar.DATE, rentalDays);
            Date dropOffDate = cal.getTime();

            Date currentDate = new Date();
            if (currentDate.equals(pickupDate)) {
                sendMessage("Good morning Jack, your car is ready. You can take it. Have a good ride 😊.");
            } else if (currentDate.equals(dropOffDate)) {
                sendMessage("Hi Jack, today is the drop off day. Please return the car today by 8 PM. Have a good day 😊.");
            }
        }
    }

    private void sendMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(update.getMessage().getChatId().toString());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
