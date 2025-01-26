package com.example.gradualmigrationapplication.message;

import com.example.migrationservice.domain.AggregateType;

import java.time.LocalDateTime;

public record LegacyDomainMessage(AggregateType aggregateType, Long aggregateId, LocalDateTime occurredOn, Long ownerId) {
}
