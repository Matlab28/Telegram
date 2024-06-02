package com.example.telegram.chatBot.qa;

import okhttp3.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionAnswerBot extends JFrame {
    private final JTextField questionField = new JTextField();
    private final JTextArea answerArea = new JTextArea();
    private final OkHttpClient client = new OkHttpClient();
    private final String openAIKey = "YOUR_OPEN_AI_KEY";

    public QuestionAnswerBot() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(600, 400);
        frame.setTitle("Question Answer Bot");

        questionField.setSize(540, 30);
        questionField.setLocation(30, 30);

        JButton askButton = new JButton("Ask");
        askButton.setSize(80, 30);
        askButton.setLocation(480, 30);
        askButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = questionField.getText();
                String answer = getOpenAIAnswer(question);
                answerArea.setText(answer);
            }
        });

        answerArea.setSize(540, 250);
        answerArea.setLocation(30, 80);
        answerArea.setEditable(false);
        answerArea.setLineWrap(true);

        frame.add(questionField);
        frame.add(askButton);
        frame.add(answerArea);
    }

    private String getOpenAIAnswer(String question) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\": \"" + question + "\"}");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + openAIKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                return extractAnswerFromJson(jsonResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error occurred while getting response from OpenAI";
    }

    private String extractAnswerFromJson(String jsonResponse) {
        Pattern pattern = Pattern.compile("\"text\": \"(.*?)\"");
        Matcher matcher = pattern.matcher(jsonResponse);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "No answer found in JSON response";
    }
}
