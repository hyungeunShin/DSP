package com.example.legacydspapplication.controller.user.dto;

import com.example.legacydspapplication.domain.user.LegacyUser;

import java.time.LocalDateTime;

public record LegacyUserResponse(
        Long id, String name, LocalDateTime createdAt,
        LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public static LegacyUserResponse from(LegacyUser user) {
        return new LegacyUserResponse(user.getId(), user.getName(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
    }
}
