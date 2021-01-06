package com.miewone.DeuNotice.Dto;

import com.miewone.DeuNotice.Domain.DeuPost;
import lombok.Builder;
import lombok.Getter;



@Getter
public class DeuPostResDto {
    private String no;
    private String title;
    private String date;
    private String url;

    public DeuPostResDto(DeuPost entity)
    {
        this.no = entity.getNo();
        this.title = entity.getTitle();
        this.date = entity.getDate();
        this.url  = entity.getUrl();
    }
}
