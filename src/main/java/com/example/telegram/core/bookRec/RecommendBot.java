//package com.example.telegram.core.bookRec;
//
//import lombok.extern.slf4j.Slf4j;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.Random;
//
//@Slf4j
//public class RecommendBot extends TelegramLongPollingBot {
//    Random random;
//
//    public RecommendBot() {
//        random = new Random();
//    }
//
//    @Override
//    public String getBotUsername() {
//        return "MyBookRecommender_bot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6416078056:AAHpKEvBo0DxMep8YghQcsnDFhSTPexjHwM";
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            Message message = update.getMessage();
//            if (message.hasText()) {
//                String textInput = message.getText();
//                processTextInput(textInput,message.getChatId(), message);
//                log.info(update.getMessage().getFrom().getFirstName());
//            }
//        }
//    }
//
//    private void processTextInput(String textInput, Long chatId, Message message) {
//        SendMessage response = new SendMessage();
//        response.setChatId(chatId.toString());
//        int generator = random.nextInt(6);
//        String romMsg = "Romance books offer a captivating exploration of love and relationships," +
//                " providing readers with an emotional and often satisfying journey into the intricacies of human " +
//                "connection.\nYou can check this\n";
//        String scFicMsg = "Science fiction books ignite imagination with futuristic worlds and thought-provoking" +
//                " themes, exploring technology, society, and humanity's " +
//                "potential in captivating ways.\nYou can check this:\n";
//        String nonFicMsg = "Non-fiction books offer real-world insights and knowledge across diverse topics, " +
//                "providing readers with valuable information and perspectives" +
//                " for learning and personal development.\nYou can check this:\n";
//
//        String romance = switch (generator) {
//            case 0 -> romMsg + Genres.ROMANCE_1.getValues();
//            case 1 -> romMsg + Genres.ROMANCE_2.getValues();
//            case 2 -> romMsg + Genres.ROMANCE_3.getValues();
//            case 3 -> romMsg + Genres.ROMANCE_4.getValues();
//            case 4 -> romMsg + Genres.ROMANCE_5.getValues();
//            case 5 -> romMsg + Genres.ROMANCE_6.getValues();
//            default -> "Invalid output...";
//        };
//
//        String scienceFiction = switch (generator) {
//            case 0 -> scFicMsg + Genres.SCIENCE_FICTION_1.getValues();
//            case 1 -> scFicMsg + Genres.SCIENCE_FICTION_2.getValues();
//            case 2 -> scFicMsg + Genres.SCIENCE_FICTION_3.getValues();
//            case 3 -> scFicMsg + Genres.SCIENCE_FICTION_4.getValues();
//            case 4 -> scFicMsg + Genres.SCIENCE_FICTION_5.getValues();
//            case 5 -> scFicMsg + Genres.SCIENCE_FICTION_6.getValues();
//            default -> "Invalid output...";
//        };
//
//        String nonFiction = switch (generator) {
//            case 0 -> nonFicMsg + Genres.NON_FICTION_1.getValues();
//            case 1 -> nonFicMsg + Genres.NON_FICTION_2.getValues();
//            case 2 -> nonFicMsg + Genres.NON_FICTION_3.getValues();
//            case 3 -> nonFicMsg + Genres.NON_FICTION_4.getValues();
//            case 4 -> nonFicMsg + Genres.NON_FICTION_5.getValues();
//            case 5 -> nonFicMsg + Genres.NON_FICTION_6.getValues();
//            default -> "Invalid output...";
//        };
//
//        if (textInput.equals("/start")) {
//            String msg = "Hi " + message.getFrom().getFirstName() + ", welcome to 'Book Recommendation'! " +
//                    "Based on your genre request, I will recommend you a book. Right now, unfortunately " +
//                    "you are available only 3 genres:\nRomance, Science-fiction, " +
//                    "and Non-Fiction.\nJust feel free to ask a genre! 📚";
//            response.setText(msg);
//        }
//
//        if (textInput.contains("romance")) {
//            response.setChatId(chatId.toString());
//            response.setText(romance);
//        } else if (textInput.contains("science-fiction") || textInput.contains("sci-fiction")) {
//            response.setChatId(chatId.toString());
//            response.setText(scienceFiction);
//        } else if (textInput.contains("non-fiction") || textInput.contains("non fiction")) {
//            response.setChatId(chatId.toString());
//            response.setText(nonFiction);
//        }
//
//        try {
//            execute(response);
//
//            // Send cover image after sending the text response
//            for (Genres genre : Genres.values()) {
//                if (textInput.contains(genre.name().toLowerCase().replace("_", "-"))) {
//                    response.setText(textInput);
//                    response.setText(genre.name().toLowerCase().replace("_", "-"));
//                   // System.out.println("User input: " + textInput);
//                    //System.out.println("Matching genre: " + genre.name().toLowerCase().replace("_", "-"));
//
//                    SendPhoto sendPhoto = new SendPhoto();
//                    sendPhoto.setChatId(chatId.toString());
//                    sendPhoto.setPhoto(new InputFile(genre.getCoverImageUrl()));
//                    execute(sendPhoto);
//                    break; // Break out of the loop after sending the cover image
//                }
//            }
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//
////        try {
////            execute(response);
////
////            // Send cover image after sending the text response
////            if (textInput.contains("romance")) {
////                SendPhoto sendPhoto = new SendPhoto();
////                sendPhoto.setChatId(chatId.toString());
////                sendPhoto.setPhoto(new InputFile(Genres.ROMANCE_1.getCoverImageUrl()));
////                execute(sendPhoto);
////            } else if (textInput.contains("science-fiction") || textInput.contains("sci-fiction")) {
////                SendPhoto sendPhoto = new SendPhoto();
////                sendPhoto.setChatId(chatId.toString());
////                sendPhoto.setPhoto(new InputFile(Genres.SCIENCE_FICTION_1.getCoverImageUrl()));
////                execute(sendPhoto);
////            } else if (textInput.contains("non-fiction") || textInput.contains("non fiction")) {
////                SendPhoto sendPhoto = new SendPhoto();
////                sendPhoto.setChatId(chatId.toString());
////                sendPhoto.setPhoto(new InputFile(Genres.NON_FICTION_1.getCoverImageUrl()));
////                execute(sendPhoto);
////            }
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        }
//    }
//
////--------------------------------------------------------------------------------------
//
////    private void processTextInput(String textInput, Long chatId, Message message) {
////        SendMessage response = new SendMessage();
////        response.setChatId(chatId.toString());
////        int generator = random.nextInt(6);
////        String romMsg = "Romance books offer a captivating exploration of love and relationships," +
////                " providing readers with an emotional and often satisfying journey into the intricacies of human " +
////                "connection.\nYou can check this\n";
////        String scFicMsg = "Science fiction books ignite imagination with futuristic worlds and thought-provoking" +
////                " themes, exploring technology, society, and humanity's " +
////                "potential in captivating ways.\nYou can check this:\n";
////        String nonFicMsg = "Non-fiction books offer real-world insights and knowledge across diverse topics, " +
////                "providing readers with valuable information and perspectives" +
////                " for learning and personal development.\nYou can check this:\n";
////
////        String romance = switch (generator) {
////            case 0 -> romMsg + Genres.ROMANCE_1.getValues();
////            case 1 -> romMsg + Genres.ROMANCE_2.getValues();
////            case 2 -> romMsg + Genres.ROMANCE_3.getValues();
////            case 3 -> romMsg + Genres.ROMANCE_4.getValues();
////            case 4 -> romMsg + Genres.ROMANCE_5.getValues();
////            case 5 -> romMsg + Genres.ROMANCE_6.getValues();
////            default -> "Invalid output...";
////        };
////
////        String scienceFiction = switch (generator) {
////            case 0 -> scFicMsg + Genres.SCIENCE_FICTION_1.getValues();
////            case 1 -> scFicMsg + Genres.SCIENCE_FICTION_2.getValues();
////            case 2 -> scFicMsg + Genres.SCIENCE_FICTION_3.getValues();
////            case 3 -> scFicMsg + Genres.SCIENCE_FICTION_4.getValues();
////            case 4 -> scFicMsg + Genres.SCIENCE_FICTION_5.getValues();
////            case 5 -> scFicMsg + Genres.SCIENCE_FICTION_6.getValues();
////            default -> "Invalid output...";
////        };
////
////        String nonFiction = switch (generator) {
////            case 0 -> nonFicMsg + Genres.NON_FICTION_1.getValues();
////            case 1 -> nonFicMsg + Genres.NON_FICTION_2.getValues();
////            case 2 -> nonFicMsg + Genres.NON_FICTION_3.getValues();
////            case 3 -> nonFicMsg + Genres.NON_FICTION_4.getValues();
////            case 4 -> nonFicMsg + Genres.NON_FICTION_5.getValues();
////            case 5 -> nonFicMsg + Genres.NON_FICTION_6.getValues();
////            default -> "Invalid output...";
////        };
////
////        if (textInput.equals("/start")) {
////            String msg = "Hi " + message.getFrom().getFirstName() + ", welcome to 'Book Recommendation'! " +
////                    "Based on your genre request, I will recommend you a book. Right now, unfortunately " +
////                    "you are available only 3 genres:\nRomance, Science-fiction, " +
////                    "and Non-Fiction.\nJust feel free to ask a genre! 📚";
////            response.setText(msg);
////        }
////
////        if (textInput.contains("romance")) {
////            response.setChatId(chatId.toString());
////            response.setText(romance);
////            // Send cover image
////            SendPhoto sendPhoto = new SendPhoto();
////            sendPhoto.setChatId(chatId.toString());
////            sendPhoto.setPhoto(new InputFile(Genres.ROMANCE_1.getCoverImageUrl()));
////            try {
////                execute(sendPhoto);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
////        } else if (textInput.contains("science-fiction") || textInput.contains("sci-fiction")) {
////            response.setChatId(chatId.toString());
////            response.setText(scienceFiction);
////            // Send cover image
////            SendPhoto sendPhoto = new SendPhoto();
////            sendPhoto.setChatId(chatId.toString());
////            sendPhoto.setPhoto(new InputFile(Genres.SCIENCE_FICTION_1.getCoverImageUrl()));
////            try {
////                execute(sendPhoto);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
////        } else if (textInput.contains("non-fiction") || textInput.contains("non fiction")) {
////            response.setChatId(chatId.toString());
////            response.setText(nonFiction);
////            // Send cover image
////            SendPhoto sendPhoto = new SendPhoto();
////            sendPhoto.setChatId(chatId.toString());
////            sendPhoto.setPhoto(new InputFile(Genres.NON_FICTION_1.getCoverImageUrl()));
////            try {
////                execute(sendPhoto);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
////        }
////
////        try {
////            execute(response);
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        }
////    }
//
//    //-----------------------------==-==-
//
////    private void processTextInput(String textInput, Long chatId, Message message) {
////        SendMessage response = new SendMessage();
////        response.setChatId(chatId.toString());
////        int generator = random.nextInt(6);
////        String romMsg = "Romance books offer a captivating exploration of love and relationships," +
////                " providing readers with an emotional and often satisfying journey into the intricacies of human " +
////                "connection.\nYou can check this\n";
////        String scFicMsg = "Science fiction books ignite imagination with futuristic worlds and thought-provoking" +
////                " themes, exploring technology, society, and humanity's " +
////                "potential in captivating ways.\nYou can check this:\n";
////        String nonFicMsg = "Non-fiction books offer real-world insights and knowledge across diverse topics, " +
////                "providing readers with valuable information and perspectives" +
////                " for learning and personal development.\nYou can check this:\n";
////
////        String romance = switch (generator) {
////            case 0 -> romMsg + Genres.ROMANCE_1.getValues();
////            case 1 -> romMsg + Genres.ROMANCE_2.getValues();
////            case 2 -> romMsg + Genres.ROMANCE_3.getValues();
////            case 3 -> romMsg + Genres.ROMANCE_4.getValues();
////            case 4 -> romMsg + Genres.ROMANCE_5.getValues();
////            case 5 -> romMsg + Genres.ROMANCE_6.getValues();
////            default -> "Invalid output...";
////        };
////
////        String scienceFiction = switch (generator) {
////            case 0 -> scFicMsg + Genres.SCIENCE_FICTION_1.getValues();
////            case 1 -> scFicMsg + Genres.SCIENCE_FICTION_2.getValues();
////            case 2 -> scFicMsg + Genres.SCIENCE_FICTION_3.getValues();
////            case 3 -> scFicMsg + Genres.SCIENCE_FICTION_4.getValues();
////            case 4 -> scFicMsg + Genres.SCIENCE_FICTION_5.getValues();
////            case 5 -> scFicMsg + Genres.SCIENCE_FICTION_6.getValues();
////            default -> "Invalid output...";
////        };
////
////        String nonFiction = switch (generator) {
////            case 0 -> nonFicMsg + Genres.NON_FICTION_1.getValues();
////            case 1 -> nonFicMsg + Genres.NON_FICTION_2.getValues();
////            case 2 -> nonFicMsg + Genres.NON_FICTION_3.getValues();
////            case 3 -> nonFicMsg + Genres.NON_FICTION_4.getValues();
////            case 4 -> nonFicMsg + Genres.NON_FICTION_5.getValues();
////            case 5 -> nonFicMsg + Genres.NON_FICTION_6.getValues();
////            default -> "Invalid output...";
////        };
////
////        if (textInput.equals("/start")) {
////            String msg = "Hi " + message.getFrom().getFirstName() + ", welcome to 'Book Recommendation'! " +
////                    "Based on your genre request, I will recommend you a book. Right now, unfortunately " +
////                    "you are available only 3 genres:\nRomance, Science-fiction, " +
////                    "and Non-Fiction.\nJust feel free to ask a genre! 📚";
////            response.setText(msg);
////        }
////
////
////        if (textInput.contains("romance")) {
////            response.setChatId(chatId.toString());
////            response.setText(romance);
////        } else if (textInput.contains("science-fiction") || textInput.contains("sci-fiction")) {
////            response.setChatId(chatId.toString());
////            response.setText(scienceFiction);
////        } else if (textInput.contains("non-fiction") || textInput.contains("non fiction")) {
////            response.setChatId(chatId.toString());
////            response.setText(nonFiction);
////        }
////
////
////        if (textInput.contains("image") || textInput.contains("picture")
////                || textInput.contains("cover") || textInput.contains("photo")) {
////            String imageUrl = "https://m.media-amazon.com/images/I/61V8g4GgqdL._AC_UF1000,1000_QL80_.jpg";
////
////            // Path where you want to save the downloaded image
////            String imagePath = "image.jpg";
//////            String imagePath = "/path/to/your/directory/61V8g4GgqdL.jpg";
////
////            try {
////                URL url = new URL(imageUrl);
////                InputStream in = url.openStream();
////                OutputStream out = new FileOutputStream(imagePath);
////
////                byte[] buffer = new byte[2048];
////                int bytesRead;
////                while ((bytesRead = in.read(buffer, 0, 2048)) != -1) {
////                    out.write(buffer, 0, bytesRead);
////                }
////                out.close();
////                in.close();
////
////                // Create a SendPhoto object and set the chat ID
////                SendPhoto sendPhoto = new SendPhoto();
////                sendPhoto.setChatId(chatId.toString());
////
////                // Set the photo to send
////                sendPhoto.setPhoto(new InputFile(new File(imagePath)));
////
////                // Send the photo
////                execute(sendPhoto);
////
////            } catch (IOException | TelegramApiException e) {
////                e.printStackTrace();
////            }
////        }
////
////
////        try {
////            execute(response);
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        }
////    }
//
//}
