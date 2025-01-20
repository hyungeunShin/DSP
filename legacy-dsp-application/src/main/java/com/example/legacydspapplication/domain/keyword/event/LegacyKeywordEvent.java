package com.example.legacydspapplication.domain.keyword.event;

import com.example.legacydspapplication.domain.AggregateType;
import com.example.legacydspapplication.domain.DomainEvent;
import com.example.legacydspapplication.domain.keyword.LegacyKeyword;

import java.time.LocalDateTime;

public abstract class LegacyKeywordEvent implements DomainEvent {
    protected LegacyKeyword legacyKeyword;

    public LegacyKeywordEvent(LegacyKeyword legacyKeyword) {
        this.legacyKeyword = legacyKeyword;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.KEYWORD;
    }

    @Override
    public Long aggregateId() {
        return legacyKeyword.getId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

    @Override
    public Long ownerId() {
        return legacyKeyword.getId();
    }
}
