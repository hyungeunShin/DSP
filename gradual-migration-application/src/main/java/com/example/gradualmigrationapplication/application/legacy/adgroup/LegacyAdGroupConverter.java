package com.example.gradualmigrationapplication.application.legacy.adgroup;

import com.example.gradualmigrationapplication.application.LegacyConverter;
import com.example.gradualmigrationapplication.domain.legacy.adgroup.LegacyAdGroup;
import com.example.gradualmigrationapplication.domain.legacy.campaign.LegacyCampaign;
import com.example.gradualmigrationapplication.domain.legacy.campaign.LegacyCampaignRepository;
import com.example.gradualmigrationapplication.domain.recent.campaign.RecentCampaign;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LegacyAdGroupConverter implements LegacyConverter<LegacyAdGroup, RecentCampaign> {
    private final LegacyCampaignRepository legacyCampaignRepository;

    @Override
    public RecentCampaign convert(LegacyAdGroup legacyAdGroup) {
        LegacyCampaign campaign = legacyCampaignRepository.findById(legacyAdGroup.getCampaignId()).orElseThrow(EntityNotFoundException::new);
        return RecentCampaign.migrated(
                legacyAdGroup.getId(), campaign.getName() + "_" + legacyAdGroup.getName(),
                legacyAdGroup.getUserId(), campaign.getBudget(), legacyAdGroup.getLinkUrl(),
                legacyAdGroup.getCreatedAt(), legacyAdGroup.getUpdatedAt(), legacyAdGroup.getDeletedAt()
        );
    }
}
