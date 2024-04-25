package com.example.telegram.core.rapidApi;

import com.example.telegram.core.travel.Links;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class Travel extends TelegramLongPollingBot {

    private static final String RAPID_API_KEY = "674678e122mshd00ec5b8f945302p1052bcjsn0ad69ed2af91";

    private static final String RAPID_API_KEY_FLIGHT = "674678e122mshd00ec5b8f945302p1052bcjsn0ad69ed2af91";

    @Override
    public String getBotUsername() {
        return "playUnoGame_bot";
    }

    @Override
    public String getBotToken() {
        return "6992258896:AAECi4glCoEwtXjHGT5dK9UCx-uR-pgNtDk";
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
            String msg = "Of course, you can check it:\n\n";
            String hotelInfo = msg + callHotelApi();
            response.setText(hotelInfo);
        } else if (textInput.toLowerCase().contains("ticket")
                || textInput.toLowerCase().contains("plane")
                || textInput.toLowerCase().contains("flight")) {
            // Call the flight API
            String flightInfo = callFlightApi();
            response.setText(flightInfo);
        } else if (textInput.toLowerCase().contains("tripadvisor")
                || textInput.toLowerCase().contains("rate")
                || textInput.toLowerCase().contains("rating")) {
            String msg = "Going before a place or restaurant it's better to check its rate. " +
                    "You can check from Tripadvisor. Here is:\n" + Links.TRIPADVISOR.getValue();
            response.setText(msg);
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String callHotelApi() {
        try {
            HttpResponse<String> response = Unirest.get("https://sky-scrapper.p.rapidapi.com/api/v1/hotels/searchDestinationOrHotel?query=new")
                    .header("X-RapidAPI-Host", "apidojo-booking-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", RAPID_API_KEY)
                    .asString();

            if (response.getStatus() == 200) {
                StringBuilder formattedResponse = new StringBuilder("Locations Related to \"New\":\n");

                JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
                JsonArray data = jsonResponse.getAsJsonArray("data");

                for (JsonElement element : data) {
                    JsonObject location = element.getAsJsonObject();
                    String locationName = location.get("entityName").getAsString();
                    String locationType = location.get("entityType").getAsString();
                    String locationHierarchy = location.get("hierarchy").getAsString();
                    formattedResponse.append("Name: ").append(locationName).append("\n");
                    formattedResponse.append("Type: ").append(locationType).append("\n");
                    formattedResponse.append("Hierarchy: ").append(locationHierarchy).append("\n");
                    formattedResponse.append("\n");
                }

                log.info("Hotels found.");
                return formattedResponse.toString();
            } else {
                log.info("Hotels not found.");
                return "Error: Failed to retrieve data from the hotel API";
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            log.info("Nothing found...");
            return "Sorry, there was an error processing your request.";
        }
    }

    public String callFlightApi() {
        try {
            HttpResponse<String> response = Unirest.get("https://flight-api.p.rapidapi.com/airlines")
                    .header("X-RapidAPI-Host", "worldwide-hotels.p.rapidapi.com")
                    .header("X-RapidAPI-Key", RAPID_API_KEY_FLIGHT)
                    .asString();

            // Process the response here
            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return "Sorry, there was an error processing your request.";
        }
    }


    @Scheduled(fixedRate = 1000)
    private void seeker() {
        callHotelApi();
    }

    // Helper method to capitalize the first letter of a string
    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
