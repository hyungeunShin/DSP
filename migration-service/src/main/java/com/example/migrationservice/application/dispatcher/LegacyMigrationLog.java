package com.example.migrationservice.application.dispatcher;

import com.example.migrationservice.domain.AggregateType;

public record LegacyMigrationLog(boolean isSuccess, Long userId, AggregateType aggregateType, Long aggregateId) {
    public static LegacyMigrationLog success(Long userId, AggregateType aggregateType, Long aggregateId) {
        return new LegacyMigrationLog(true, userId, aggregateType, aggregateId);
    }

    public static LegacyMigrationLog fail(Long userId, AggregateType aggregateType, Long aggregateId) {
        return new LegacyMigrationLog(false, userId, aggregateType, aggregateId);
    }
}
