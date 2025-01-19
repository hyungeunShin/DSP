package com.example.legacydspapplication.domain.adgroup;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LegacyAdGroupTest {
    LegacyAdGroup adGroup = LegacyAdGroup.of("adGroup", 1L, 1L, "http://localhost:8080");

    @Test
    void updateLinkUrl() throws InterruptedException {
        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(100);
        adGroup.updateLinkUrl("https://naver.com");
        Thread.sleep(100);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(adGroup.getLinkUrl()).isEqualTo("https://naver.com"),
                () -> assertThat(adGroup.getUpdatedAt()).isAfter(before).isBefore(after)
        );
    }
}