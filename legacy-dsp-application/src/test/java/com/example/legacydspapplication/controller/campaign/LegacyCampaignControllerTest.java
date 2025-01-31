package com.example.legacydspapplication.controller.campaign;

import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignCreateRequest;
import com.example.legacydspapplication.controller.campaign.dto.LegacyCampaignResult;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

class LegacyCampaignControllerTest {
    RestTemplate restTemplate = new RestTemplate();
    Random random = new Random();

    @Test
    void test() {
        for(int i = 0; i < 1000; i++) {
            String name = "campaign" + (random.nextInt(1000) + 1);
            long userId = random.nextInt(1000) + 1;
            long budget = (random.nextInt(990001) + 10000);

            var result = restTemplate.postForEntity("http://localhost:8080/campaigns/v1", new LegacyCampaignCreateRequest(name, userId, budget), LegacyCampaignResult.class);
            if(result.getStatusCode().isError()) {
                System.out.println("error : " + result.getStatusCode());
            }
            if(i % 100 == 0) {
                System.out.println("progress : " + i);
            }
        }
    }
}