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
                + "동의대학교 사회복지학과 새글 알림 서비스 시작\n"
                + "=== SERVER START === \n\n"
                + "\n[Up-Time] : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        //특정 프로필일 때만 전송 처리
        telegramMessageBot.sendMessage(startMsg);
    }

    @EventListener
    public void shutdownApplicationEvent(ContextClosedEvent event) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), TimeZone.getDefault().toZoneId());
        String startMsg = "\n===== Application =====\n"
                + "=== SERVER DOWN === \n\n"
                + "동의대학교 사회복지학과 새글 알림 서비스 종료"
                + "\n[Down-Time] : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        telegramMessageBot.sendMessage(startMsg);
        telegramMessageBot.onClosing();
    }
}
