package com.example.legacydspapplication.domain.keyword.event;

import com.example.legacydspapplication.domain.keyword.LegacyKeyword;

import java.time.LocalDateTime;

public class LegacyKeywordDeletedEvent extends LegacyKeywordEvent {
    public LegacyKeywordDeletedEvent(LegacyKeyword legacyKeyword) {
        super(legacyKeyword);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyKeyword.getDeletedAt();
    }
}
