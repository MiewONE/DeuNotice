package com.miewone.DeuNotice.Service;


import lombok.Data;
import lombok.Getter;
import org.jsoup.Connection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Data
public class TelegramSender{

    private final String AUTH_KEY = "1591890100:AAFak6qektt9FSZP87H47Owt2ssN_2w920w";
    private final String chatId; //1223486878


}
