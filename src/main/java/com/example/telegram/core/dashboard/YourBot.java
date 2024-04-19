package com.example.telegram.core.dashboard;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URL;


public class YourBot extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "7018040278:AAGM69dNsb7jTpyT2YkQZDIAcPsBRS_NXKY";  // Replace with your actual token

    @Override
    public String getBotUsername() {
        return "guesssNumber_bot";  // Replace with your chosen username
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Process incoming updates
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String textInput = message.getText();
                processTextInput(textInput, message.getChatId());
            }
        }
    }

    private void processTextInput(String textInput, Long chatId) {

// It works!!!!
        if (textInput.contains("image") || textInput.contains("picture")
                || textInput.contains("cover") || textInput.contains("photo")) {
            String imageUrl = "https://m.media-amazon.com/images/I/61V8g4GgqdL._AC_UF1000,1000_QL80_.jpg";

            // Path where you want to save the downloaded image
            String imagePath = "image.jpg";
//            String imagePath = "/path/to/your/directory/61V8g4GgqdL.jpg";

            try {
                URL url = new URL(imageUrl);
                InputStream in = url.openStream();
                OutputStream out = new FileOutputStream(imagePath);

                byte[] buffer = new byte[2048];
                int bytesRead;
                while ((bytesRead = in.read(buffer, 0, 2048)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                in.close();

                // Create a SendPhoto object and set the chat ID
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId.toString());

                // Set the photo to send
                sendPhoto.setPhoto(new InputFile(new File(imagePath)));

                // Send the photo
                execute(sendPhoto);

            } catch (IOException | TelegramApiException e) {
                e.printStackTrace();
            }
        }


        // Handle the received text input here (e.g., perform actions, send responses)
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());

//        if (textInput.equalsIgnoreCase("music")) {
//            String message = "Here is:\nhttps://music.apple.com/us/browse";
//            response.setText(message);
//        }

        if (textInput.contains("music")) {
            String message = "Here is:\nhttps://music.apple.com/us/browse";
            response.setText(message);
        }

//        response.setText("You entered: " + textInput);

        try {
            execute(response); // Send a response message
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

