package com.example.batchmigrationapplication.application.message;

import com.example.migrationservice.domain.AggregateType;
import com.example.migrationservice.domain.migration.PageMigrationEvent;

public record PageMigrationMessage(Long userId, AggregateType aggregateType, boolean isFinished) {
    public static PageMigrationMessage from(PageMigrationEvent event) {
        return new PageMigrationMessage(event.userId(), event.aggregateType(), event.isFinished());
    }
}
