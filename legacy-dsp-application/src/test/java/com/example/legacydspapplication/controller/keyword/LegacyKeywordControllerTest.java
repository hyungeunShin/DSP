package com.example.legacydspapplication.controller.keyword;

import com.example.legacydspapplication.controller.keyword.dto.LegacyKeywordCreateRequest;
import com.example.legacydspapplication.controller.keyword.dto.LegacyKeywordResult;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class LegacyKeywordControllerTest {
    RestTemplate restTemplate = new RestTemplate();
    Random random = new Random();

    @Test
    void test() throws InterruptedException {
        for (int i = 1; i <= 20; i++) {
            create(i);
        }
    }

    void create(int step) throws InterruptedException {
        try(ExecutorService executorService = Executors.newFixedThreadPool(1000)) {
            AtomicInteger count = new AtomicInteger(0);
            int threadCount = 5000000 - count.get();
            CountDownLatch latch = new CountDownLatch(threadCount);
            for(int i = 0; i < threadCount; i++) {
                executorService.execute(() -> {
                    createKeyword(count, step);
                    latch.countDown();
                });
            }
            latch.await();
        }
    }

    private void createKeyword(AtomicInteger count, int step) {
        try {
            String text = "keyword" + (random.nextInt(100) + 1);
            long adGroupId = random.nextInt(1000000) + 1;
            long userId = random.nextInt(1000) + 1;

            var result = restTemplate.postForEntity("http://localhost:8080/keywords/v1", new LegacyKeywordCreateRequest(text, adGroupId, userId), LegacyKeywordResult.class);
            if(result.getStatusCode().isError()) {
                System.out.println("error:" + result.getStatusCode());
            }
            if(count.incrementAndGet() % 100 == 0) {
                System.out.println("[" + step + "] progress: " + count + " at " + LocalDateTime.now());
            }
        } catch(Exception e) {
            createKeyword(count, step);
        }
    }
}