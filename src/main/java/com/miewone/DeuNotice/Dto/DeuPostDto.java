package com.miewone.DeuNotice.Dto;

import com.miewone.DeuNotice.Domain.DeuPost;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class DeuPostDto {

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
    
    private String hostUrl;
    private String name;
    private Boolean groupYN;
    @Builder
    public DeuPostDto(Long id,Long chatId,String url,String baseurl,String department,String dateFommat,String docElement,String noticeClass,String titleClass,String dateClass,String title,String hostUrl,String name,Boolean groupYN)
    {
        this.id = id;
        this.chatId =chatId;
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
    }

    public DeuPost toEntity()
    {
        return DeuPost.builder()
                .id(id)
                .chatId(chatId)
                .url(url)
                .baseurl(baseurl)
                .department(department)
                .dateFommat(dateFommat)
                .docElement(docElement)
                .noticeClass(noticeClass)
                .titleClass(titleClass)
                .dateClass(dateClass)
                .title(title)
                .name(name)
                .hostUrl(hostUrl)
                .groupYN(groupYN)
                .build();
    }

}
