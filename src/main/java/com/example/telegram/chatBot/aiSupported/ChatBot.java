package com.example.telegram.chatBot.aiSupported;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

@Slf4j
@Setter
@Getter
public class ChatBot extends JFrame {
    private JTextArea chatArea = new JTextArea();
    private JTextField chatBox = new JTextField();
    private Random random;

    public ChatBot() {
        random = new Random();
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
                int elect = random.nextInt(4);
                int vehicle = random.nextInt(2);
                String gText = chatBox.getText();
                log.info("User responded");
                chatArea.append("You -> " + gText + "\n");
                chatBox.setText(" ");
                String msg = "Here's you can check it:\n";

                String electronics = switch (elect) {
                    case 0 -> Links.ELECTRONICS_1.getValue();
                    case 1 -> Links.ELECTRONICS_2.getValue();
                    case 2 -> Links.ELECTRONICS_3.getValue();
                    case 3 -> Links.ELECTRONICS_4.getValue();
                    default -> "Invalid output...";
                };

                String cars = switch (vehicle) {
                    case 0 -> Links.CAR_1.getValue();
                    case 1 -> Links.CAR_2.getValue();
                    default -> "Invalid output...";
                };

                if (gText.toLowerCase().contains("hello")
                        || gText.toLowerCase().contains("hi")
                        || gText.toLowerCase().contains("greeting")) {
                    bot("Hello, how can I help you?");
                } else if (gText.toLowerCase().contains("electronics") || gText.toLowerCase().contains("device")) {
                    bot(msg + electronics);
                    log.info("Bot responded");
                } else if (gText.toLowerCase().contains("building")
                        || gText.toLowerCase().contains("house")
                        || gText.toLowerCase().contains("home")
                        || gText.toLowerCase().contains("place")) {
                    bot(msg + Links.BUILDING.getValue());
                    log.info("Bot responded");
                } else if (gText.toLowerCase().contains("car") || gText.toLowerCase().contains("vehicle")) {
                    bot(msg + cars);
                    log.info("Bot responded");
                } else if (gText.toLowerCase().contains("thanks")
                        || gText.toLowerCase().contains("thank you")
                        || gText.toLowerCase().contains("appreciated")) {
                    bot("You are welcome 🙂.");
                    log.info("Bot responded");
                }

            }
        });
    }

    private void bot(String string) {
        chatArea.append("Bot -> " + string + "\n");
    }
}
