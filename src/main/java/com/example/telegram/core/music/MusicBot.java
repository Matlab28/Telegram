package com.example.telegram.core.music;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
public class MusicBot extends TelegramLongPollingBot {
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
            String msg = "Hi " + message.getFrom().getFirstName() + ", in this bot I will help you the" +
                    " best music for you!";
            response.setText(msg);
        }

        if (textInput.toLowerCase().contains("album")) {
            String albums = albumSpotify();
            response.setText(albums);
        }

        if (textInput.toLowerCase().contains("spotify")) {
            String clarification = "What do you want to get exactly?\nLike albums, artist," +
                    " tracks, playlists, genres, or podcasts? Please clarify choosing one of them 🎵";
            response.setChatId(chatId.toString());
            response.setText(clarification);


        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String albumSpotify() {
        try {
            HttpResponse<String> response = Unirest.get("https://spotify23.p.rapidapi.com/albums/?ids=3IBcauSj5M2A6lTeffJzdv")
                    .header("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                    .header("X-RapidAPI-Key", ApiLinks.RAPID_API_KEY.getValue())
                    .asString();

            if (response.getStatus() == 200) {
                JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();

                if (jsonResponse.has("data")) {
                    JsonArray data = jsonResponse.getAsJsonArray("data");

                    if (data.size() == 0) {
                        log.warn("No albums found matching the criteria.");
                        return "No albums found matching the criteria.";
                    }

                    StringBuilder formattedResponse = new StringBuilder("Albums Related to \"New\":\n");
                    for (JsonElement element : data) {
                        JsonObject album = element.getAsJsonObject();
                        String albumName = album.get("name").getAsString();
                        String albumArtist = album.get("artist").getAsString();
                        formattedResponse.append("Album: ").append(albumName).append("\n");
                        formattedResponse.append("Artist: ").append(albumArtist).append("\n");
                        formattedResponse.append("\n");
                    }
                    log.info("Spotify albums found.");
                    return formattedResponse.toString();
                } else {
                    log.warn("No 'data' field found in the Spotify API response.");
                    return "No data found in the Spotify API response.";
                }
            } else {
                log.error("Failed to retrieve data from the Spotify API. Status code: {}", response.getStatus());
                return "Error: Failed to retrieve data from the Spotify API";
            }
        } catch (UnirestException e) {
            log.error("Error processing request to Spotify API: {}", e.getMessage());
            return "Sorry, there was an error processing your request.";
        }
    }
}


