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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String nowTime;
    private String comDate;
    private final TelegramBot telegramBot;
    private final PostService service;

    @Scheduled(fixedDelay=5 *1000)
    public void ppomPpu_notice()
    {
        for(DeuPost member : service.getMembers())
        {
            if(member.getDepartment().equals("뽐뿌"))
            {
                get_Ppom(member.getChatId(), member.getUrl(), member.getBaseurl(),member.getDateFommat(),member.getDocElement(),member.getNoticeClass(),member.getTitleClass(),member.getDateClass(),member.getTitle(),member.getHostUrl(),member.getLinkClass());
            }

        }
    }
//        @Scheduled(fixedDelay=20 *1000)
    @Scheduled(cron = "0 0 18 * * ?")
    public void Haksk_Notice()
    {
        //https://www.deu.ac.kr/www/board/3
        //http://socialwelfare.deu.ac.kr/sub0201

//        List<Long> ids = service.getIds();
        for(DeuPost member : service.getMembers())
        {
            get(member.getChatId(), member.getUrl(), member.getBaseurl(),member.getDateFommat(),member.getDocElement(),member.getNoticeClass(),member.getTitleClass(),member.getDateClass(),member.getTitle(),member.getHostUrl(),member.getLinkClass());
        }
    }


    private void get(Long id,String url,String baseUrl,String dateFommat,String docElement,String noticeClass,String titleClass,String dateClass,String title,String hostUrl,String linkClass)
    {
        LocalDate current = LocalDate.now();
        LocalDateTime currentTime = LocalDateTime.now();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFommat);
        DateTimeFormatter format_time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String test = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String[] splitDate =test.split(" ");
        nowDate = splitDate[0];
        nowTime = splitDate[1];


        List<getinfo> posts = new ArrayList<>();
        try{
            Document doc = Jsoup.parse(new URL(url).openStream(), "utf-8",baseUrl);


            Elements postelems = doc.select(docElement);
            //boardList

            int i=0;
//            telegramBot.sendMessage("hi");
            for(Element post : postelems)
            {
//                String[] tess = post.select("a").outerHtml().split("\"");
                String[] tesd =post.select("a").text().split("\""); // 뽐뿌 타이틀 ㅇ ,동의대 ㅇ
                String[] tess = post.select(linkClass).outerHtml().split("\""); // 뽐뿌 링크가져오기
                if(tess[1].contains("amp;"))
                {
                    tess[1] = tess[1].replaceAll("amp;","");
                }

                posts.add(new getinfo(
                        post.select(noticeClass).text()
//                        ,post.select(titleClass).text()
                        ,tesd[0]
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
    private void get_Ppom(Long id,String url,String baseUrl,String dateFommat,String docElement,String noticeClass,String titleClass,String dateClass,String title,String hostUrl,String linkClass)
    {
        LocalDate current = LocalDate.now();
        LocalDateTime currentTime = LocalDateTime.now();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFommat);
        DateTimeFormatter format_time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String test = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String[] splitDate =test.split(" ");
        nowDate = splitDate[0];
        nowTime = splitDate[1];
        String[] timeNow = nowTime.split(":");


        List<getinfo> posts = new ArrayList<>();
        try{
            Document doc = Jsoup.parse(new URL(url).openStream(), "euc-kr",baseUrl);


            Elements postelems = doc.select(docElement);
            //boardList

            int i=0;
//            telegramBot.sendMessage("hi");
            for(Element post : postelems)
            {
//                String[] tess = post.select("a").outerHtml().split("\"");
                String[] tesd =post.select("a").text().split("\""); // 뽐뿌 타이틀 ㅇ ,동의대 ㅇ
                String[] tess = post.select(linkClass).outerHtml().split("\""); // 뽐뿌 링크가져오기
                if(tess[1].contains("amp;"))
                {
                    tess[1] = tess[1].replaceAll("amp;","");
                }

                posts.add(new getinfo(
                        post.select(noticeClass).text()
//                        ,post.select(titleClass).text()
                        ,tesd[0]
                        ,post.select(dateClass).text()
                        ,tess[1]
                ));
//                telegramBot.sendMessage(id,title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+"http://socialwelfare.deu.ac.kr/"+tess[1]);
                if(post.select(dateClass).text().contains(timeNow[0])&&post.select(dateClass).text().contains(timeNow[1]))
                {
                    telegramBot.sendMessage(id,title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+hostUrl+tess[1]);
                }

                i++;
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
