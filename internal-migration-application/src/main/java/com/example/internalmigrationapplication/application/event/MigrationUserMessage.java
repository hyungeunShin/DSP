package com.example.internalmigrationapplication.application.event;

import com.example.migrationservice.domain.migration.user.event.MigrationUserEvent;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;

public record MigrationUserMessage(Long userId, MigrationUserStatus status, MigrationUserStatus prevStatus) {
    public static MigrationUserMessage from(MigrationUserEvent event) {
        return new MigrationUserMessage(event.getUserId(), event.getStatus(), event.getPrevStatus());
    }
}
