package com.example.legacydspapplication.application.event;

import com.example.legacydspapplication.domain.AggregateType;
import com.example.legacydspapplication.domain.DomainEvent;

import java.time.LocalDateTime;

public record LegacyDomainMessage(AggregateType aggregateType, Long aggregateId, LocalDateTime occurredOn, Long ownerId) {
    public static LegacyDomainMessage from(DomainEvent event) {
        return new LegacyDomainMessage(event.aggregateType(), event.aggregateId(), event.occurredOn(), event.ownerId());
    }
}
