package com.example.legacydspapplication.domain.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LegacyUserTest {
    LegacyUser user = LegacyUser.of("name");

    @Test
    void updateName() throws InterruptedException {
        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(100);
        user.updateName("newName");
        Thread.sleep(100);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(user.getName()).isEqualTo("newName"),
                () -> assertThat(user.getUpdatedAt()).isAfter(before).isBefore(after)
        );
    }

    @Test
    void delete() throws InterruptedException {
        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(100);
        user.delete();
        Thread.sleep(100);
        LocalDateTime after = LocalDateTime.now();

        assertThat(user.getDeletedAt()).isAfter(before).isBefore(after);
    }
}