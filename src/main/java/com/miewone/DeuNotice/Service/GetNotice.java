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
    private final TelegramBot telegramBot;
    @Scheduled(fixedDelay = 1 * 60 * 1000,initialDelay = 3000)
    public void Haksk_Notice()
    {
        //http://socialwelfare.deu.ac.kr/sub0201
        this.url = "http://socialwelfare.deu.ac.kr/sub0201";
        getNotice("학사");
        this.url= "http://socialwelfare.deu.ac.kr/sub0301";
        getNotice("실습");
    }
    public void Practice_Notice()
    {
        getNotice("실습");
    }

    private List<DeuPost> getNotice(String title)
    {
        if(nowDate==null)
        {
            LocalDate current = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.M.d");
            nowDate = current.format(format);
        }

        List<DeuPost> posts = new ArrayList<>();
        try{
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8","http://socialwelfare.deu.ac.kr/index.php");

            Elements Notices = doc.select(".notice");
            Elements elem = doc.select(".title");
            Elements postelems = doc.select("tbody tr");

            int i=0;
//            telegramBot.sendMessage("hi");
            for(Element post : postelems)
            {
                String[] tess = post.select("a").outerHtml().split("\"");
                posts.add(new DeuPost(
                        post.select(".no").text()
                        ,post.select(".title").text()
                        ,post.select(".time").text()
                        ,tess[1]
                ));
//                telegramBot.sendMessage(title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+"http://socialwelfare.deu.ac.kr/"+tess[1]);
                if(post.select(".time").text().equals(nowDate))
                {
                    telegramBot.sendMessage(title+" 게시판\nNo : "+posts.get(i).getNo()+"\n제목 : "+posts.get(i).getTitle()+"\n날짜 : "+posts.get(i).getDate()+"\n링크 : "+"http://socialwelfare.deu.ac.kr/"+tess[1]);
                }

                i++;
            }
            ;

            List<String> noticeList =  getElement(elem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return posts;
    }
    private List<String> getElement(Elements elements)
    {
        List<String> returnsList = new ArrayList<String>();
        for(Element el : elements)
        {
            if(el.text().equals("제목"))
            {
                continue;
            }
            returnsList.add(el.text());
        }
        return returnsList;
    }
}
