package com.miewone.DeuNotice;

import com.miewone.DeuNotice.Dto.DeuPostDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeuNoticeApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void crollingTest()
    {
        DeuPostDto dto = DeuPostDto.builder()
                .id(1223486878L)
                .department("동의대공지")
                .groupYN(true)
                .name("박원균")
                .build();
        String url ="http://localhost:"+port+"/api/info";

        ResponseEntity<String> resEntity =testRestTemplate.postForEntity(url,dto,String.class);
    }
    @Test
    void contextLoads() {

    }

}
