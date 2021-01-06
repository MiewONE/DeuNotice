package com.miewone.DeuNotice.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;


@Slf4j
@Component
@RequiredArgsConstructor
public class StartUpApplicationListener {
    private final TelegramBot telegramMessageBot;


    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), TimeZone.getDefault().toZoneId());
        String startMsg = "\n===== Application =====\n"
                + "동의대학교 \n"
                + "새글 알림 서비스 시작\n\n\n"
                + "현재 등록된 학과\n\n사회복지학과\n컴퓨터 소프트웨어 공학과\n"
                + "=== SERVER START === \n\n"
                + "\n[Up-Time] : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        //특정 프로필일 때만 전송 처리
        telegramMessageBot.sendMessage(startMsg);
    }

    @EventListener
    public void shutdownApplicationEvent(ContextClosedEvent event) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), TimeZone.getDefault().toZoneId());
        String startMsg = "\n===== Application =====\n"
                + "동의대학교 \n"
                + "새글 알림 서비스 시작\n\n\n"
                + "현재 등록된 학과\n\n사회복지학과\n컴퓨터 소프트웨어 공학과\n"
                + "=== SERVER DOWN === \n\n"
                + "\n[Up-Time] : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        telegramMessageBot.sendMessage(startMsg);
        telegramMessageBot.onClosing();
    }
}
