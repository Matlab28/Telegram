package com.example.telegram.chatBot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class Bot extends JFrame {
    private final JTextArea chatArea = new JTextArea();
    private final JTextField chatBox = new JTextField();

    public Bot() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setTitle("ChatBot");
        frame.add(chatArea);
        frame.add(chatBox);
        chatArea.setSize(500, 400);
        chatArea.setLocation(2, 2);

        chatBox.setSize(540, 30);
        chatBox.setLocation(2, 500);
        chatArea.setBackground(Color.lightGray);

        chatBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gText = chatBox.getText().toLowerCase();
                log.info("User responded");
                chatArea.append("You -> " + gText + "\n");
                chatBox.setText(" ");

                if (gText.contains("hi")) {
                    schedule();
                    log.info("Bot responded");
                } else {
                    bot("I am able to answer only 'hi'.");
                }
            }
        });
    }

    private void bot(String string) {
        chatArea.append("Bot -> " + string + "\n");
    }

    @Scheduled(fixedRate = 1000)
    public void schedule() {
        bot("Hello World!");
    }
}
