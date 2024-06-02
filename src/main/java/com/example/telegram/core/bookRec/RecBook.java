package com.example.telegram.core.bookRec;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Slf4j
public class RecBook extends TelegramLongPollingBot {
    Random random;

    public RecBook() {
        random = new Random();
    }

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
        int generator = random.nextInt(6);
        String romMsg = "Romance books offer a captivating exploration of love and relationships," +
                " providing readers with an emotional and often satisfying journey into the intricacies of human " +
                "connection.\nYou can check this\n";
        String scFicMsg = "Science fiction books ignite imagination with futuristic worlds and thought-provoking" +
                " themes, exploring technology, society, and humanity's " +
                "potential in captivating ways.\nYou can check this:\n";
        String nonFicMsg = "Non-fiction books offer real-world insights and knowledge across diverse topics, " +
                "providing readers with valuable information and perspectives" +
                " for learning and personal development.\nYou can check this:\n";

        String romance = switch (generator) {
            case 0 -> romMsg + Genres.ROMANCE_1.getValues() + "\n" + Links.ROMANCE_1.getCoverImageUrl();
            case 1 -> romMsg + Genres.ROMANCE_2.getValues() + "\n" + Links.ROMANCE_2.getCoverImageUrl();
            case 2 -> romMsg + Genres.ROMANCE_3.getValues() + "\n" + Links.ROMANCE_3.getCoverImageUrl();
            case 3 -> romMsg + Genres.ROMANCE_4.getValues() + "\n" + Links.ROMANCE_4.getCoverImageUrl();
            case 4 -> romMsg + Genres.ROMANCE_5.getValues() + "\n" + Links.ROMANCE_5.getCoverImageUrl();
            case 5 -> romMsg + Genres.ROMANCE_6.getValues() + "\n" + Links.ROMANCE_6.getCoverImageUrl();
            default -> "Invalid output...";
        };

        String scienceFiction = switch (generator) {
            case 0 ->
                    scFicMsg + Genres.SCIENCE_FICTION_1.getValues() + "\n" + Links.SCIENCE_FICTION_1.getCoverImageUrl();
            case 1 ->
                    scFicMsg + Genres.SCIENCE_FICTION_2.getValues() + "\n" + Links.SCIENCE_FICTION_2.getCoverImageUrl();
            case 2 ->
                    scFicMsg + Genres.SCIENCE_FICTION_3.getValues() + "\n" + Links.SCIENCE_FICTION_3.getCoverImageUrl();
            case 3 ->
                    scFicMsg + Genres.SCIENCE_FICTION_4.getValues() + "\n" + Links.SCIENCE_FICTION_4.getCoverImageUrl();
            case 4 ->
                    scFicMsg + Genres.SCIENCE_FICTION_5.getValues() + "\n" + Links.SCIENCE_FICTION_5.getCoverImageUrl();
            case 5 ->
                    scFicMsg + Genres.SCIENCE_FICTION_6.getValues() + "\n" + Links.SCIENCE_FICTION_6.getCoverImageUrl();
            default -> "Invalid output...";
        };

        String nonFiction = switch (generator) {
            case 0 -> nonFicMsg + Genres.NON_FICTION_1.getValues() + "\n" + Links.NON_FICTION_1.getCoverImageUrl();
            case 1 -> nonFicMsg + Genres.NON_FICTION_2.getValues() + "\n" + Links.NON_FICTION_2.getCoverImageUrl();
            case 2 -> nonFicMsg + Genres.NON_FICTION_3.getValues() + "\n" + Links.NON_FICTION_3.getCoverImageUrl();
            case 3 -> nonFicMsg + Genres.NON_FICTION_4.getValues() + "\n" + Links.NON_FICTION_4.getCoverImageUrl();
            case 4 -> nonFicMsg + Genres.NON_FICTION_5.getValues() + "\n" + Links.NON_FICTION_5.getCoverImageUrl();
            case 5 -> nonFicMsg + Genres.NON_FICTION_6.getValues() + "\n" + Links.NON_FICTION_6.getCoverImageUrl();
            default -> "Invalid output...";
        };

        if (textInput.equals("/start")) {
            String msg = "Hi " + message.getFrom().getFirstName() + ", welcome to 'Book Recommendation'! " +
                    "Based on your genre request, I will recommend you a book. Right now, unfortunately " +
                    "you are available only 3 genres:\nRomance, Science-fiction, " +
                    "and Non-Fiction.\nJust feel free to ask a genre! 📚";
            response.setText(msg);
        }

        if (textInput.toLowerCase().contains("romance")) {
            response.setChatId(chatId.toString());
            response.setText(romance);
        } else if (textInput.toLowerCase().contains("science-fiction")
                || textInput.toLowerCase().contains("sci-fiction")
                || textInput.toLowerCase().contains("science fiction")) {
            response.setChatId(chatId.toString());
            response.setText(scienceFiction);
        } else if (textInput.toLowerCase().contains("non-fiction")
                || textInput.toLowerCase().contains("non fiction")) {
            response.setChatId(chatId.toString());
            response.setText(nonFiction);
        }
//        else {
//            response.setChatId(chatId.toString());
//            String msg = "Unfortunately we have only 3 categories (romance, science-fiction, and non-fiction)." +
//                    " Please select one of them...";
//            response.setText(msg);
//        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
