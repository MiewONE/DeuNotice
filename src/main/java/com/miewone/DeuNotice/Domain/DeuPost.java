package com.miewone.DeuNotice.Domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class DeuPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long chatId;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String baseurl;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private String dateFommat;
    @Column(nullable = false)
    private String docElement;
    @Column(nullable = false)
    private String noticeClass;
    @Column(nullable = false)
    private String titleClass;
    @Column(nullable = false)
    private String dateClass;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String hostUrl;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean groupYN;
    @Column(nullable = false)
    private String linkClass;
    @Builder
    public DeuPost(Long id,Long chatId,String url,String baseurl,String department,String dateFommat,String docElement,String noticeClass,String titleClass,String dateClass,String title,String hostUrl,String name,Boolean groupYN,String linkClass)
    {
        this.id = id;
        this.chatId = chatId;
        this.url =url;
        this.baseurl = baseurl;
        this.department = department;
        this.dateFommat =dateFommat;
        this.docElement = docElement;
        this.noticeClass = noticeClass;
        this.titleClass= titleClass;
        this.dateClass = dateClass;
        this.title = title;
        this.hostUrl = hostUrl;
        this.name = name;
        this.groupYN = groupYN;
        this.linkClass = linkClass;
    }
//    @Id
//    private String id;
//    @Column(nullable = false)(unique = true)
//    private String title;
//    @Column(nullable = false)
//    private String date;
//    @Column(nullable = false)
//    private String url;
//    @Builder
//    public DeuPost(String id,String title,String date,String url)
//    {
//        this.id = id;
//        this.title =title;
//        this.date = date;
//        this.url = url;
//    }

}
