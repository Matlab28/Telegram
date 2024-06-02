package com.example.telegram.core.music;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class SongBot extends TelegramLongPollingBot {

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
                processTextInput(textInput, message.getChatId());
                log.info("Received message from: {}", update.getMessage().getFrom().getFirstName());
            }
        }
    }

    private void processTextInput(String textInput, Long chatId) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());

        log.info("Received text input: {}", textInput);

        if (textInput.equals("/start")) {
            String msg = "Hi there! I'm here to help you find music. What would you like to search for?";
            response.setText(msg);
        } else if (textInput.toLowerCase().contains("spotify")) {
            if (textInput.toLowerCase().contains("album")) {
                String albums = fetchAlbumsFromSpotify();
                log.info("Albums from Spotify API: {}", albums);
                if (albums != null && !albums.isEmpty()) {
                    response.setText(albums);
                } else {
                    response.setText("No albums found on Spotify.");
                }
            } else {
                String clarification = "What would you like to search on Spotify? (e.g., albums, artists, tracks)";
                response.setText(clarification);
            }
        }

        log.info("Response before execution: {}", response);

        try {
            execute(response);
        } catch (Exception e) {
            log.error("Error sending response to user: {}", e.getMessage());
        }
    }

    private String fetchAlbumsFromSpotify() {
        try {
            HttpResponse<String> response = Unirest.get("https://spotify23.p.rapidapi.com/albums/?ids=3IBcauSj5M2A6lTeffJzdv")
                    .header("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "YOUR_RAPIDAPI_KEY_HERE")
                    .asString();

            log.info("Spotify API response body: {}", response.getBody()); // Add logging here

            if (response.getStatus() == 200) {
                JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
                JsonArray albums = jsonResponse.getAsJsonArray("albums");

                log.info("Received albums from Spotify API: {}", albums);

                if (albums != null && albums.size() > 0) {
                    StringBuilder formattedResponse = new StringBuilder("Albums Related to \"New\":\n");

                    for (JsonElement albumElement : albums) {
                        JsonObject album = albumElement.getAsJsonObject();
                        String albumName = album.get("name").getAsString();
                        String albumArtist = album.get("artists").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
                        formattedResponse.append("Album: ").append(albumName).append("\n");
                        formattedResponse.append("Artist: ").append(albumArtist).append("\n\n");
                    }

                    log.info("Spotify albums found.");
                    return formattedResponse.toString();
                } else {
                    log.warn("No albums found in the Spotify API response.");
                    return "No albums found on Spotify.";
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

    @Scheduled
    private void seeker() {
        fetchAlbumsFromSpotify();
    }

}
