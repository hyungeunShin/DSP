package com.example.legacydspapplication.domain.adgroup.event;

import com.example.legacydspapplication.domain.AggregateType;
import com.example.legacydspapplication.domain.DomainEvent;
import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public abstract class LegacyAdGroupEvent implements DomainEvent {
    protected LegacyAdGroup adGroup;

    public LegacyAdGroupEvent(LegacyAdGroup adGroup) {
        this.adGroup = adGroup;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.ADGROUP;
    }

    @Override
    public Long aggregateId() {
        return adGroup.getId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

    @Override
    public Long ownerId() {
        return adGroup.getId();
    }
}
