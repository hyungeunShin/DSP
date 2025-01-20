package com.example.legacydspapplication.domain.user.event;

import com.example.legacydspapplication.domain.user.LegacyUser;

import java.time.LocalDateTime;

public class LegacyUserCreatedEvent extends LegacyUserEvent {
    public LegacyUserCreatedEvent(LegacyUser legacyUser) {
        super(legacyUser);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyUser.getCreatedAt();
    }
}
