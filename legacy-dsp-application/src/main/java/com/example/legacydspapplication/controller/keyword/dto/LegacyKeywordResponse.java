package com.example.legacydspapplication.controller.keyword.dto;

import com.example.legacydspapplication.domain.keyword.LegacyKeyword;

import java.time.LocalDateTime;

public record LegacyKeywordResponse(
        Long id, String text, Long adGroupId, Long userId,
        LocalDateTime createdAt, LocalDateTime deletedAt) {
    public static LegacyKeywordResponse from(LegacyKeyword keyword) {
        return new LegacyKeywordResponse(keyword.getId(), keyword.getText(), keyword.getAdGroupId(),
                keyword.getUserId(), keyword.getCreatedAt(), keyword.getDeletedAt());
    }
}
