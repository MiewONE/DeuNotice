package com.miewone.DeuNotice.Dto;

import com.miewone.DeuNotice.Domain.DeuPost;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeuPostDto {
    private String no;
    private String title;
    private String date;
    private String url;
    @Builder
    public DeuPostDto(String no,String title,String date,String url)
    {
        this.no =no;
        this.title = title;
        this.date = date;
        this.url = url;
    }

    public DeuPost toEntity()
    {
        return DeuPost.builder()
                .no(no)
                .title(title)
                .date(date)
                .url(url)
                .build();
    }

}
