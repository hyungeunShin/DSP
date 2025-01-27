package com.example.internalmigrationapplication.controller.migration;

import com.example.migrationservice.domain.AggregateType;

public record MigrationRetryRequest(Long userId, Long aggregateId, AggregateType aggregateType) {
}
