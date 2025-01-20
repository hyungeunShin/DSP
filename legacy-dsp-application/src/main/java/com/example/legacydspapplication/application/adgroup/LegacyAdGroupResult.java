package com.example.legacydspapplication.application.adgroup;

import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public record LegacyAdGroupResult(
        Long id, String name, Long userId, Long campaignId, String linkUrl,
        LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public static LegacyAdGroupResult from(LegacyAdGroup group) {
        return new LegacyAdGroupResult(group.getId(), group.getName(), group.getUserId(), group.getCampaignId(), group.getLinkUrl(),
                group.getCreatedAt(), group.getUpdatedAt(), group.getDeletedAt());
    }
}
