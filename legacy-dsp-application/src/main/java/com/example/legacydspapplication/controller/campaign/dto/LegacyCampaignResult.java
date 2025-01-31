package com.example.legacydspapplication.controller.campaign.dto;

import com.example.legacydspapplication.domain.campaign.LegacyCampaign;

import java.time.LocalDateTime;

public record LegacyCampaignResult(
        Long id, String name, Long userId, Long budget,
        LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public static LegacyCampaignResult from(LegacyCampaign campaign) {
        return new LegacyCampaignResult(campaign.getId(), campaign.getName(), campaign.getUserId(), campaign.getBudget(), campaign.getCreatedAt(), campaign.getUpdatedAt(), campaign.getDeletedAt());
    }
}
