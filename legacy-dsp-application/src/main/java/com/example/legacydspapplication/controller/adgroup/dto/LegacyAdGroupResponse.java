package com.example.legacydspapplication.controller.adgroup.dto;

import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;

import java.time.LocalDateTime;

public record LegacyAdGroupResponse(
        Long id, String name, Long userId, Long campaignId, String linkUrl,
        LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public static LegacyAdGroupResponse from(LegacyAdGroup group) {
        return new LegacyAdGroupResponse(group.getId(), group.getName(), group.getUserId(), group.getCampaignId(), group.getLinkUrl(),
                group.getCreatedAt(), group.getUpdatedAt(), group.getDeletedAt());
    }
}
