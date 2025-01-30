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

    @Test
    @DisplayName("재시도하면 상태를 RETRIED로 바꾸고 이전 상태를 저장")
    void test2() throws InterruptedException {
        MigrationUser user = MigrationUser.agreed(1L);
        user.progressMigration();

        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(1);
        user.retry();
        Thread.sleep(1);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(user.getStatus()).isEqualTo(MigrationUserStatus.RETRIED),
                () -> assertThat(user.getUpdatedAt()).isAfter(before).isBefore(after),
                () -> assertThat(user.getPrevStatus()).isEqualTo(MigrationUserStatus.USER_FINISHED)
        );
    }

    @Test
    @DisplayName("재시도하고 다음 도메인 마이그레이션 진행할 때는 이전 상태의 다음 상태로 변경")
    void test3() {
        MigrationUser user = MigrationUser.agreed(1L);
        user.progressMigration();
        user.retry();
        user.progressMigration();

        assertAll(
                () -> assertThat(user.getStatus()).isEqualTo(MigrationUserStatus.ADGROUP_FINISHED),
                () -> assertThat(user.getPrevStatus()).isEqualTo(MigrationUserStatus.USER_FINISHED)
        );
    }

    @Test
    @DisplayName("이미 재시도한 경우 업데이트 시간만 변경")
    void test4() throws InterruptedException {
        MigrationUser user = MigrationUser.agreed(1L);
        user.progressMigration();
        user.retry();

        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(1);
        user.retry();
        Thread.sleep(1);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(user.getStatus()).isEqualTo(MigrationUserStatus.RETRIED),
                () -> assertThat(user.getUpdatedAt()).isAfter(before).isBefore(after),
                () -> assertThat(user.getPrevStatus()).isEqualTo(MigrationUserStatus.USER_FINISHED)
        );
    }
}