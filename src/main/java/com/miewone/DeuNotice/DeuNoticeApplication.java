package com.miewone.DeuNotice;

import com.miewone.DeuNotice.Service.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class DeuNoticeApplication {

    public static void main(String[] args) {

        ApiContextInitializer.init();
//
//        //2
//        TelegramBotsApi botsApi = new TelegramBotsApi();
//        try {
//            botsApi.registerBot(new TelegramBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
        SpringApplication.run(DeuNoticeApplication.class, args);
    }

}
