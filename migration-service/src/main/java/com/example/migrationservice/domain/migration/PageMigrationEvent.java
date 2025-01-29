package com.example.migrationservice.domain.migration;

import com.example.migrationservice.domain.AggregateType;

public record PageMigrationEvent(AggregateType aggregateType, Long userId, boolean isFinished) {
}
