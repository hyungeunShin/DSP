package com.example.migrationservice.application.monitor;

import com.example.migrationservice.domain.migration.PageMigrationTestable;
import com.example.migrationservice.domain.migration.user.MigrationUser;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MigrationMonitorTest {
    @Test
    void test1() {
        MigrationMonitor.MigrationUsers users = MigrationMonitor.MigrationUsers.of(List.of(MigrationUser.agreed(1L), MigrationUser.agreed(2L), progressed(3L, 1), progressed(4L, 2)));
        assertThat(users.statusStatistics()).isEqualTo(
                Map.of(MigrationUserStatus.AGREED, 2L, MigrationUserStatus.USER_FINISHED, 1L, MigrationUserStatus.ADGROUP_FINISHED, 1L)
        );
    }

    @Test
    void test2() {
        MigrationMonitor.PageMigrations pageMigrations = MigrationMonitor.PageMigrations.of(
                List.of(
                        new PageMigrationTestable(1L, 4, 10, 100L),
                        new PageMigrationTestable(2L, 3, 10, 100L),
                        new PageMigrationTestable(3L, 0, 10, 0L)
                )
        );

        Long result = pageMigrations.migratedCount();

        assertThat(result).isEqualTo(90);
    }

    @Test
    void test3() {
        MigrationMonitor.PageMigrations pageMigrations = MigrationMonitor.PageMigrations.of(
                List.of(
                        new PageMigrationTestable(1L, 4, 10, 100L),
                        new PageMigrationTestable(2L, 3, 10, 100L),
                        new PageMigrationTestable(3L, 0, 10, 0L)
                )
        );

        Long result = pageMigrations.totalCount();

        assertThat(result).isEqualTo(200);
    }

    private MigrationUser progressed(Long userId, int num) {
        MigrationUser user = MigrationUser.agreed(userId);
        for(int i = 0; i < num; i++) {
            user.progressMigration();
        }
        return user;
    }
}