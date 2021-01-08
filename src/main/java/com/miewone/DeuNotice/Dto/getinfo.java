package com.miewone.DeuNotice.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class getinfo {
    private String no;

    private String title;

    private String date;

    private String url;
    public getinfo(String no,String title,String date,String url)
    {
        this.no = no;
        this.title =title;
        this.date = date;
        this.url = url;
    }
}
