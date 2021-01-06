package com.miewone.DeuNotice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KaKaoAth {
    private RestTemplate restTemplate = new RestTemplate();

    String url = "https://kauth.kakao.com/oauth/authorize";
    ResponseEntity<String> res = restTemplate.getForEntity(url,String.class);

}
