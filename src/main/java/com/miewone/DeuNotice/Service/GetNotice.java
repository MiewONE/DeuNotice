package com.miewone.DeuNotice.Service;

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
import java.util.Map;

@Data
@Service
@RequiredArgsConstructor
public class GetNotice{
    private String url="";
    public void Haksk_Notice()
    {
        //http://socialwelfare.deu.ac.kr/sub0201
        /*
        *  String URL = "https://weather.naver.com/rgn/cityWetrMain.nhn";
        Document doc = Jsoup.connect(URL).get();
        Elements elem = doc.select(".tbl_weather tbody>tr:nth-child(1)");
        String[] str = elem.text().split(" ");
        Elements elem2=doc.select(".tbl_weather tbody>tr:nth-child(1) img");
        * */
        try{
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8","http://socialwelfare.deu.ac.kr/index.php");
            Elements elem = doc.select(".title");
            String[] str = elem.text().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void Practice_Notice()
    {

    }
}
