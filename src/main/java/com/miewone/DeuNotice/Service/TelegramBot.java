package com.miewone.DeuNotice.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
public class TelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME = "AlterDeuNotice_bot"; //Bot Name
    private final String AUTH_KEY = "1591890100:AAFak6qektt9FSZP87H47Owt2ssN_2w920w"; //Bot Auth-Key
    private Long CHAT_ID = 1223486878L; //Chat ID
    // 지윤이 1591879752
    @Override
    public void onUpdateReceived(Update update) {
        this.CHAT_ID = update.getMessage().getChatId();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return AUTH_KEY;
    }
    //id 1223486878
    public void sendMessage(String sendMessage) {
        SendMessage message = new SendMessage()
                .setChatId(CHAT_ID)
                .setText(sendMessage);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
