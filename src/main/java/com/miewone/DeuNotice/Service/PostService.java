package com.miewone.DeuNotice.Service;

import com.miewone.DeuNotice.Domain.DeuPost;
import com.miewone.DeuNotice.Domain.DeuPostRepository;
import com.miewone.DeuNotice.Dto.DeuPostDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.source.spi.IdentifierSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final DeuPostRepository deuPostRepository;

    @Transactional
    public void save(DeuPostDto dto)
    {
        //여기서 사이트가 더 추가될 경우 entity를 하나더 만들어 그 안에 각 사이트의 url,baseurl,datefommat, ...을 넣어두고 한번 저장되어 있으면 불러와서 하게끔 하자.
        switch (dto.getDepartment())
        {
            case "동의대공지":
                deuPostRepository.save(setdto(dto,"https://www.deu.ac.kr/www/board/3","https://www.deu.ac.kr/www","yyyy.MM.dd","tbody tr","th",".text-left","td:nth-child(4)","학교 공지","https://www.deu.ac.kr/").toEntity());
                break;
            case "동의대사복학사":
                deuPostRepository.save(setdto(dto,"http://socialwelfare.deu.ac.kr/sub0201","http://socialwelfare.deu.ac.kr/index.php","yyyy.M.d","tbody tr",".no",".title",".time","사회복지학과 학사","http://socialwelfare.deu.ac.kr").toEntity());
                break;
            case "동의대사복실습":
                deuPostRepository.save(setdto(dto,"http://socialwelfare.deu.ac.kr/sub0301","http://socialwelfare.deu.ac.kr/index.php","yyyy.M.d","tbody tr",".no",".title",".time","사회복지학과 실습","http://socialwelfare.deu.ac.kr").toEntity());
                break;
            case "동의대컴소공":
                deuPostRepository.save(setdto(dto,"https://se.deu.ac.kr/bbs/board.php?bo_table=notice","https://se.deu.ac.kr/","M-d","tbody tr",".sound_only",".td_subject",".td_datetime","컴퓨터 소프트웨어 공학과","").toEntity());
                break;
            case "동의대영어영문":
                deuPostRepository.save(setdto(dto,"http://english.deu.ac.kr/notice","http://english.deu.ac.kr/","yyyy-MM-dd",".boardList tbody tr",".notice .notice",".title",".date","영어 영문학과 공지","http://english.deu.ac.kr").toEntity());
                break;
            case "동의대중국어":
                deuPostRepository.save(setdto(dto,"http://chi.deu.ac.kr/notice","http://chi.deu.ac.kr/","yyyy-MM-dd",".boardList tbody tr",".notice .notice",".title",".date","중국어학과 공지","http://chi.deu.ac.kr").toEntity());
                break;
            default:
                break;
        }
    }

    public List<DeuPost> getMembers()
    {
            return deuPostRepository.findAll();
    }
    public List<Long> getIds()
    {
        return deuPostRepository.findbyAllId();
    }
    public List<String> getDepartment()
    {
        return deuPostRepository.findAllByDepartment();
    }

    private DeuPostDto setdto(DeuPostDto dto,String url,String baseurl,String datefommat,String docElement,String noticeClass,String titleClass,String dateClass,String title,String hostUrl)
    {
        dto.setUrl(url);
        dto.setBaseurl(baseurl);
        dto.setDateFommat(datefommat);
        dto.setDocElement(docElement);
        dto.setNoticeClass(noticeClass);
        dto.setTitleClass(titleClass);
        dto.setDateClass(dateClass);
        dto.setTitle(title);
        dto.setHostUrl(hostUrl);
        return dto;
    }
}
