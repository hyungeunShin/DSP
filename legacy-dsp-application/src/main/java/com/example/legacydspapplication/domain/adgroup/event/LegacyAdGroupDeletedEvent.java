package com.example.legacydspapplication.domain.adgroup.event;

import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public class LegacyAdGroupDeletedEvent extends LegacyAdGroupEvent {
    public LegacyAdGroupDeletedEvent(LegacyAdGroup adGroup) {
        super(adGroup);
    }

    @Override
    public LocalDateTime occurredOn() {
        return adGroup.getDeletedAt();
    }
}
