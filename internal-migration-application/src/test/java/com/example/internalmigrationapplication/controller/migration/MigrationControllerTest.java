package com.example.internalmigrationapplication.controller.migration;

import com.example.internalmigrationapplication.controller.user.MigrationUserResult;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

class MigrationControllerTest {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void test1() {
        for(int i = 1; i <= 100; i++) {
            var result = restTemplate.postForEntity("http://localhost:8082/migration/v1/user/" + i + "/agree", new HttpEntity<>(headers()), MigrationUserResult.class);

            if(result.getStatusCode().isError()) {
                System.out.println("error : " + result.getStatusCode());
            }
        }
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}