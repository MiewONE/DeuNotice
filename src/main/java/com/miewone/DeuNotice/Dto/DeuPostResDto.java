package com.miewone.DeuNotice.Dto;

import com.miewone.DeuNotice.Domain.DeuPost;
import lombok.Builder;
import lombok.Getter;

import javax.swing.text.StyledEditorKit;


@Getter
public class DeuPostResDto {
    private Long id;
    private Long chatId;
    private String url;

    private String baseurl;
    private String department;
    private String dateFommat;

    private String docElement;

    private String noticeClass;

    private String titleClass;

    private String dateClass;

    private String title;
    private String linkClass;
    private String hostUrl;
    private String name;
    private Boolean groupYN;
    public DeuPostResDto(DeuPost entity)
    {
        this.id = entity.getId();
        this.chatId = entity.getChatId();
        this.url = entity.getUrl();
        this.baseurl =entity.getBaseurl();
        this.department = entity.getDepartment();
        this.dateFommat = entity.getDateFommat();
        this.docElement = entity.getDocElement();
        this.noticeClass = entity.getNoticeClass();
        this.titleClass = entity.getTitleClass();
        this.dateClass = entity.getDateClass();
        this.title = entity.getTitle();
        this.hostUrl = entity.getHostUrl();
        this.name = entity.getName();
        this.groupYN = entity.getGroupYN();
        this.linkClass=entity.getLinkClass();
    }
}
