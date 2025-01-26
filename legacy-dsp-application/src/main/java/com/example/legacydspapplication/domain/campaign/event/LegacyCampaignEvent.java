package com.example.legacydspapplication.domain.campaign.event;

import com.example.legacydspapplication.domain.AggregateType;
import com.example.legacydspapplication.domain.DomainEvent;
import com.example.legacydspapplication.domain.campaign.LegacyCampaign;

import java.time.LocalDateTime;

public abstract class LegacyCampaignEvent implements DomainEvent {
    protected LegacyCampaign legacyCampaign;

    public LegacyCampaignEvent(LegacyCampaign legacyCampaign) {
        this.legacyCampaign = legacyCampaign;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.CAMPAIGN;
    }

    @Override
    public Long aggregateId() {
        return legacyCampaign.getId();
    }

    @Override
    public abstract LocalDateTime occurredOn();

    @Override
    public Long ownerId() {
        return legacyCampaign.getUserId();
    }
}
