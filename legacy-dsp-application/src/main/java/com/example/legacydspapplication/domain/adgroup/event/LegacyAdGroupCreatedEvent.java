package com.example.legacydspapplication.domain.adgroup.event;

import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public class LegacyAdGroupCreatedEvent extends LegacyAdGroupEvent {
    public LegacyAdGroupCreatedEvent(LegacyAdGroup adGroup) {
        super(adGroup);
    }

    @Override
    public LocalDateTime occurredOn() {
        return adGroup.getCreatedAt();
    }
}
