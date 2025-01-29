package com.example.migrationservice.application.dispatcher;

import com.example.migrationservice.domain.AggregateType;

public record PageMigrationLog(
        Long userId, AggregateType aggregateType, Integer pageNumber,
        Integer totalPage, Long totalCount, boolean isSuccess) {
    public static PageMigrationLog success(PageMigrationResult result, AggregateType aggregateType) {
        return new PageMigrationLog(result.userId(), aggregateType, result.pageNumber(), result.totalPage(), result.totalCount(), true);
    }

    public static PageMigrationLog fail(PageMigrationResult result, AggregateType aggregateType) {
        return new PageMigrationLog(result.userId(), aggregateType, result.pageNumber(), result.totalPage(), result.totalCount(), false);
    }
}
