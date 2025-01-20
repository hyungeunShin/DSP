package com.example.legacydspapplication.controller.adgroup.dto;

public record LegacyAdGroupCreateRequest(String name, Long userId, Long campaignId, String linkUrl) {
}
