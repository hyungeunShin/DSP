package com.example.internalmigrationapplication.controller.user;

import com.example.migrationservice.domain.migration.user.MigrationUser;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;

import java.time.LocalDateTime;

public record MigrationUserResult(Long id, MigrationUserStatus status, MigrationUserStatus prevStatus, LocalDateTime agreedAt, LocalDateTime updatedAt) {
    public static MigrationUserResult from(MigrationUser user) {
        return new MigrationUserResult(user.getId(), user.getStatus(), user.getPrevStatus(), user.getAgreedAt(), user.getUpdatedAt());
    }
}
