package com.example.legacydspapplication.controller.user.dto;

import com.example.legacydspapplication.domain.user.LegacyUser;

import java.time.LocalDateTime;

public record LegacyUserResult(
        Long id, String name, LocalDateTime createdAt,
        LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public static LegacyUserResult from(LegacyUser user) {
        return new LegacyUserResult(user.getId(), user.getName(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
    }
}
