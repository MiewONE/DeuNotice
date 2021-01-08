package com.miewone.DeuNotice.Contorller;

import com.miewone.DeuNotice.Dto.DeuPostDto;
import com.miewone.DeuNotice.Service.GetNotice;
import com.miewone.DeuNotice.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class restapi {
    private final PostService postService;

    @PostMapping("/api/info")
    public String noticeInfo(@RequestBody DeuPostDto dto)
    {
        postService.save(dto);
        return "";
    }
}
