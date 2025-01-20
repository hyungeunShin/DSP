package com.example.legacydspapplication.domain.campaign.event;

import com.example.legacydspapplication.domain.campaign.LegacyCampaign;

import java.time.LocalDateTime;

public class LegacyCampaignNameUpdatedEvent extends LegacyCampaignEvent {
    public LegacyCampaignNameUpdatedEvent(LegacyCampaign legacyCampaign) {
        super(legacyCampaign);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyCampaign.getUpdatedAt();
    }
}
