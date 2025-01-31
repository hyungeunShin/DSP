package com.example.migrationservice.application.monitor;

import com.example.migrationservice.domain.migration.user.MigrationUser;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public record MigrationMonitorMetrics(
        Map<MigrationUserStatus, Long> statusStatistics,
        Long adGroupMigratedCount, Long adGroupTotalCount,
        Long keywordMigratedCount, Long keywordTotalCount
        ) {
    public static MigrationMonitorMetrics from(MigrationMonitor.MigrationUsers migrationUsers, MigrationMonitor.PageMigrations adGroupPages, MigrationMonitor.PageMigrations keywordPages) {
        return new MigrationMonitorMetrics(migrationUsers.statusStatistics(), adGroupPages.migratedCount(), adGroupPages.totalCount(), keywordPages.migratedCount(), keywordPages.totalCount());
    }
}
