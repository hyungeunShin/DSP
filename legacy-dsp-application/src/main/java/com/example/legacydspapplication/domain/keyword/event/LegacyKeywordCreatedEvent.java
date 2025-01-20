package com.example.legacydspapplication.domain.keyword.event;

import com.example.legacydspapplication.domain.keyword.LegacyKeyword;

import java.time.LocalDateTime;

public class LegacyKeywordCreatedEvent extends LegacyKeywordEvent {
    public LegacyKeywordCreatedEvent(LegacyKeyword legacyKeyword) {
        super(legacyKeyword);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyKeyword.getCreatedAt();
    }
}
