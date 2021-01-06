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
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Service
@RequiredArgsConstructor
public class GetNotice{
    private String url="";
    private final TelegramBot telegramBot;
    public List<DeuPost> Haksk_Notice()
    {
        //http://socialwelfare.deu.ac.kr/sub0201
        /*
        *  String URL = "https://weather.naver.com/rgn/cityWetrMain.nhn";
        Document doc = Jsoup.connect(URL).get();
        Elements elem = doc.select(".tbl_weather tbody>tr:nth-child(1)");
        String[] str = elem.text().split(" ");
        Elements elem2=doc.select(".tbl_weather tbody>tr:nth-child(1) img");
        * */
        List<DeuPost> posts = new ArrayList<>();
        try{
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8","http://socialwelfare.deu.ac.kr/index.php");

            Elements Notices = doc.select(".notice");
            Elements elem = doc.select(".title");
            Elements postelems = doc.select("tbody tr");

//            telegramBot.sendMessage("hi");
            for(Element post : postelems)
            {
                posts.add(new DeuPost(
                        post.select(".no").text()
                        ,post.select(".title").text()
                        ,post.select(".time").text()));
            }
            List<String> postList =  getElement(Notices);
            String ss = elem.get(0).text();

            List<String> noticeList =  getElement(elem);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return posts;
    }
    public void Practice_Notice()
    {

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
