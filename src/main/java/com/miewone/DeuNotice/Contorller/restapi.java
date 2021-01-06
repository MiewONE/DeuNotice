package com.miewone.DeuNotice.Contorller;

import com.miewone.DeuNotice.Service.GetNotice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class restapi {
    private final GetNotice getNotice;

    @GetMapping("/api/info")
    public String noticeInfo()
    {
        getNotice.setUrl("http://socialwelfare.deu.ac.kr/sub0201");
        getNotice.Haksk_Notice();
        return "";
    }
}
