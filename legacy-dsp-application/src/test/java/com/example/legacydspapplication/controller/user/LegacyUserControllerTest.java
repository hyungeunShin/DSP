package com.example.legacydspapplication.controller.user;

import com.example.legacydspapplication.controller.user.dto.LegacyUserCreateRequest;
import com.example.legacydspapplication.controller.user.dto.LegacyUserResult;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

class LegacyUserControllerTest {
    RestTemplate restTemplate = new RestTemplate();
    Random random = new Random();

    @Test
    void test() {
        for(int i = 0; i < 1000; i++) {
            String name = "user" + (random.nextInt(1000) + 1);
            var result = restTemplate.postForEntity("http://localhost:8080/users/v1", new LegacyUserCreateRequest(name), LegacyUserResult.class);
            if(result.getStatusCode().isError()) {
                System.out.println("error : " + result.getStatusCode());
            }
            if(i % 100 == 0) {
                System.out.println("progress : " + i);
            }
        }
    }
}