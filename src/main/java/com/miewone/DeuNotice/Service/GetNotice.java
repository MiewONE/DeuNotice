package com.miewone.DeuNotice.Service;

import com.miewone.DeuNotice.Domain.DeuPost;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Service
@RequiredArgsConstructor
public class GetNotice{
    private String url="";
    private String nowDate;
    private String comDate;
    private final TelegramBot telegramBot;
    @Scheduled(fixedDelay = 1 * 60 * 1000,initialDelay = 3000)
    public void Haksk_Notice()
    {
        //https://www.deu.ac.kr/www/board/3
        //http://socialwelfare.deu.ac.kr/sub0201
        this.url ="https://www.deu.ac.kr/www/board/3";
        get("https://www.deu.ac.kr/www","yyyy.MM.dd","th",".text-left","td:nth-child(4)","학교 공지","https://www.deu.ac.kr/");
        this.url = "http://socialwelfare.deu.ac.kr/sub0201";
        get("http://socialwelfare.deu.ac.kr/index.php","yyyy.M.d",".no",".title",".time","사회복지학과 학사","http://socialwelfare.deu.ac.kr");
        this.url= "http://socialwelfare.deu.ac.kr/sub0301";
        get("http://socialwelfare.deu.ac.kr/index.php","yyyy.M.d",".no",".title",".time","사회복지학과 학사","http://socialwelfare.deu.ac.kr");
        this.url = "https://se.deu.ac.kr/bbs/board.php?bo_table=notice";
        get("https://se.deu.ac.kr/","M-d",".sound_only",".td_subject",".td_datetime","컴퓨터 소프트웨어 공학과","");
    }


    private void get(String baseUrl,String dateFommat,String noticeClass,String titleClass,String dateClass,String title,String hostUrl)
    {
        if(nowDate==null)
        {
            LocalDate current = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFommat);
            nowDate = current.format(format);
        }

        List<DeuPost> posts = new ArrayList<>();
        try{
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8",baseUrl);


            Elements postelems = doc.select("tbody tr");

            int i=0;
//            telegramBot.sendMessage("hi");
            for(Element post : postelems)
            {
                String[] tess = post.select("a").outerHtml().split("\"");
                posts.add(new DeuPost(
                        post.select(noticeClass).text()
                        ,post.select(titleClass).text()
                        ,post.select(dateClass).text()
                        ,tess[1]
                ));
                telegramBot.sendMessage(title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+"http://socialwelfare.deu.ac.kr/"+tess[1]);
                if(post.select(dateClass).text().equals(nowDate))
                {
                    telegramBot.sendMessage(title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+hostUrl+tess[1]);
                }

                i++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
