package com.example.legacydspapplication.domain.adgroup.event;

import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public class LegacyAdGroupNameUpdatedEvent extends LegacyAdGroupEvent {
    public LegacyAdGroupNameUpdatedEvent(LegacyAdGroup adGroup) {
        super(adGroup);
    }

    @Override
    public LocalDateTime occurredOn() {
        return adGroup.getUpdatedAt();
    }
}
