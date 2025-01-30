package com.example.batchmigrationapplication.application.message;

import com.example.migrationservice.domain.migration.user.MigrationUserStatus;
import com.example.migrationservice.domain.migration.user.event.MigrationUserEvent;

public record MigrationUserMessage(Long userId, MigrationUserStatus status, MigrationUserStatus prevStatus) {
    public static MigrationUserMessage from(MigrationUserEvent event) {
        return new MigrationUserMessage(event.getUserId(), event.getStatus(), event.getPrevStatus());
    }
}
