package com.example.telegram.core.rentACar.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TelegramConfig {

    @Value("${cron.expression}")
    private String cronExpression;

    @Scheduled(cron = "${cron.expression}")
    public void scheduledReminder() {

    }
}
