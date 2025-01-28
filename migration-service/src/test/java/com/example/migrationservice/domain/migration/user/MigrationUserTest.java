package com.example.migrationservice.domain.migration.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MigrationUserTest {
    @Test
    @DisplayName("다음 도메인 마이그레이션 진행")
    void test1() throws InterruptedException {
        MigrationUser user = MigrationUser.agreed(1L);

        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(1);
        user.progressMigration();
        Thread.sleep(1);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(user.getStatus()).isEqualTo(MigrationUserStatus.AGREED.next()),
                () -> assertThat(user.getUpdatedAt()).isAfter(before).isBefore(after),
                () -> assertThat(user.getPrevStatus()).isEqualTo(MigrationUserStatus.AGREED)
        );
    }
}