package com.example.telegram.core.dashboard;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

public class YourTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // Send a specific picture from a URL
        String photoUrl = "https://share.icloud.com/photos/0c3S7jQUDBniihWILGMmqnzCQ"; // Replace this with your actual photo URL

        // Send the photo back to the user
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        try {

            sendPhoto.setPhoto(new InputFile(String.valueOf(downloadPhoto(photoUrl))));
            execute(sendPhoto);
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream downloadPhoto(String photoUrl) throws IOException {
        // Download the photo from the URL and return it as InputStream
        URL url = new URL(photoUrl);
        return url.openStream();
    }

//    @Override
//    public void onUpdateReceived(Update update) {
//        // Check if the update has a message with a photo
//        if (update.hasMessage() && update.getMessage().hasPhoto()) {
//            // Get the photo id
//            List<PhotoSize> photos = update.getMessage().getPhoto();
//            String photoId = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getFileId();
//
//            // Send the photo back to the user
//            SendPhoto sendPhoto = new SendPhoto();
//            sendPhoto.setChatId(update.getMessage().getChatId().toString());
//            sendPhoto.setPhoto(new InputFile(photoId));
//            try {
//                execute(sendPhoto);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public String getBotUsername() {
        return "MyBookRecommender_bot";
    }

    @Override
    public String getBotToken() {
        return "6416078056:AAHpKEvBo0DxMep8YghQcsnDFhSTPexjHwM";
    }
}

