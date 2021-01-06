package com.miewone.DeuNotice.Domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class DeuPost {
    @Id
    private String no;
    @Column(unique = true)
    private String title;
    @Column
    private String date;
    @Column
    private String url;
    @Builder
    public DeuPost(String no,String title,String date,String url)
    {
        this.no = no;
        this.title =title;
        this.date = date;
        this.url = url;
    }

}
