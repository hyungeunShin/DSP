package com.example.legacydspapplication.controller.adgroup;

import com.example.legacydspapplication.application.adgroup.LegacyAdGroupResult;
import com.example.legacydspapplication.controller.adgroup.dto.LegacyAdGroupCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class LegacyAdGroupControllerTest {
    RestTemplate restTemplate = new RestTemplate();
    Random random = new Random();

    @Test
    void test() throws InterruptedException {
        try(ExecutorService service = Executors.newFixedThreadPool(1000)) {
            AtomicInteger count = new AtomicInteger(0);
            int totalCount = 1000000;
            CountDownLatch latch = new CountDownLatch(totalCount);
            for(int i = 0; i < totalCount; i++) {
                service.execute(() -> {
                    create(count);
                    latch.countDown();
                });
            }
            latch.await();
        }
    }

    void create(AtomicInteger i) {
        try {
            String name = "adgroup" + (random.nextInt(1000) + 1);
            long userId = random.nextInt(1000) + 1;
            long campaignId = random.nextInt(1000) + 1;
            String linkUrl = "https://naver.com";

            var result = restTemplate.postForEntity("http://localhost:8080/adgroups/v1", new LegacyAdGroupCreateRequest(name, userId, campaignId, linkUrl), LegacyAdGroupResult.class);
            if(result.getStatusCode().isError()) {
                System.out.println("error : " + result.getStatusCode());
            }
            if(i.incrementAndGet() % 100 == 0) {
                System.out.println("progress : " + i);
            }
        } catch(Exception e) {
            create(i);
        }
    }
}