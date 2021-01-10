package com.miewone.DeuNotice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final String BOT_NAME = "AlterDeuNotice_bot"; //Bot Name
    private final String AUTH_KEY = "1591890100:AAFak6qektt9FSZP87H47Owt2ssN_2w920w"; //Bot Auth-Key
    private List<Long> CHAT_ID = new ArrayList<>();// = 1223486878L; //Chat ID-498852328L;//
    public String Date;


    public void addIds(Long id)
    {
        this.CHAT_ID.add(id);
    }
    public List<Long> getIds()
    {
        return CHAT_ID;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage().getChatId()==1223486878L)//공지
        {
            for(Long ids :CHAT_ID)
            {
                sendMessage(1223486878L,update.getMessage().getText());
            }
        }

//        sendMessage("chatId"+this.CHAT_ID+this.Date);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return AUTH_KEY;
    }

    public String getDate(){return Date;}
    //id 1223486878
    public void sendMessage(Long id ,String sendMessage) {
        SendMessage message = new SendMessage()
                .setChatId(id)
                .setText(sendMessage);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
