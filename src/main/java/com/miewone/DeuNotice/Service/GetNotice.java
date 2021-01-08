package com.miewone.DeuNotice.Service;

import com.miewone.DeuNotice.Domain.DeuPost;
import com.miewone.DeuNotice.Dto.getinfo;
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
    private final PostService service;

//        @Scheduled(fixedDelay=20 *1000)
    @Scheduled(cron = "0 0 18 * * ?")
    public void Haksk_Notice()
    {
        //https://www.deu.ac.kr/www/board/3
        //http://socialwelfare.deu.ac.kr/sub0201

//        List<Long> ids = service.getIds();
        for(DeuPost member : service.getMembers())
        {
            get(member.getId(), member.getUrl(), member.getBaseurl(),member.getDateFommat(),member.getDocElement(),member.getNoticeClass(),member.getTitleClass(),member.getDateClass(),member.getTitle(),member.getHostUrl());
        }
        //현주 1574989305
//        this.url ="https://www.deu.ac.kr/www/board/3";
//        for(Long id : ids)//전체공지
//        {
//            get(id,"","https://www.deu.ac.kr/www","yyyy.MM.dd","tbody tr","th",".text-left","td:nth-child(4)","학교 공지","https://www.deu.ac.kr/");
//        }
//        this.url = "http://socialwelfare.deu.ac.kr/sub0201";
//        get(ids.get(0),"http://socialwelfare.deu.ac.kr/index.php","yyyy.M.d","tbody tr",".no",".title",".time","사회복지학과 학사","http://socialwelfare.deu.ac.kr");
//        this.url= "http://socialwelfare.deu.ac.kr/sub0301";
//        get(ids.get(0),"http://socialwelfare.deu.ac.kr/index.php","yyyy.M.d","tbody tr",".no",".title",".time","사회복지학과 학사","http://socialwelfare.deu.ac.kr");
//        this.url = "https://se.deu.ac.kr/bbs/board.php?bo_table=notice";
//        get(ids.get(0),"https://se.deu.ac.kr/","M-d","tbody tr",".sound_only",".td_subject",".td_datetime","컴퓨터 소프트웨어 공학과","");
//        this.url="http://english.deu.ac.kr/notice";
//        get(ids.get(0),"http://english.deu.ac.kr/","yyyy-MM-dd",".boardList tbody tr",".notice .notice",".title",".date","영어 영문학과 공지","http://english.deu.ac.kr");
    }


    private void get(Long id,String url,String baseUrl,String dateFommat,String docElement,String noticeClass,String titleClass,String dateClass,String title,String hostUrl)
    {
        LocalDate current = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFommat);
        nowDate = current.format(format);


        List<getinfo> posts = new ArrayList<>();
        try{
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8",baseUrl);


            Elements postelems = doc.select(docElement);
            //boardList

            int i=0;
//            telegramBot.sendMessage("hi");
            for(Element post : postelems)
            {
                String[] tess = post.select("a").outerHtml().split("\"");
                posts.add(new getinfo(
                        post.select(noticeClass).text()
                        ,post.select(titleClass).text()
                        ,post.select(dateClass).text()
                        ,tess[1]
                ));
//                telegramBot.sendMessage(id,title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+"http://socialwelfare.deu.ac.kr/"+tess[1]);
                if(post.select(dateClass).text().equals(nowDate))
                {
                    telegramBot.sendMessage(id,title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+hostUrl+tess[1]);
                }

                i++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
