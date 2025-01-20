package com.example.legacydspapplication.controller.campaign.dto;

import com.example.legacydspapplication.domain.campaign.LegacyCampaign;

import java.time.LocalDateTime;

public record LegacyCampaignResponse(
        Long id, String name, Long userId, Long budget,
        LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public static LegacyCampaignResponse from(LegacyCampaign campaign) {
        return new LegacyCampaignResponse(campaign.getId(), campaign.getName(), campaign.getUserId(), campaign.getBudget(), campaign.getCreatedAt(), campaign.getUpdatedAt(), campaign.getDeletedAt());
    }
}
