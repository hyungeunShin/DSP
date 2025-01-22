package com.example.gradualmigrationapplication.application.legacy.campaign;

import com.example.gradualmigrationapplication.application.LegacyMigrationService;
import com.example.gradualmigrationapplication.application.legacy.adgroup.LegacyAdGroupMigrationService;
import com.example.gradualmigrationapplication.domain.legacy.adgroup.LegacyAdGroup;
import com.example.gradualmigrationapplication.domain.legacy.adgroup.LegacyAdGroupRepository;
import com.example.gradualmigrationapplication.domain.legacy.campaign.LegacyCampaign;
import com.example.gradualmigrationapplication.domain.legacy.campaign.LegacyCampaignRepository;
import com.example.gradualmigrationapplication.domain.recent.campaign.RecentCampaign;
import com.example.gradualmigrationapplication.domain.recent.campaign.RecentCampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyCampaignMigrationService extends LegacyMigrationService<LegacyCampaign, RecentCampaign> {
    private final LegacyAdGroupRepository legacyAdGroupRepository;
    private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;

    public LegacyCampaignMigrationService(
            LegacyCampaignRepository legacyRepository, RecentCampaignRepository recentRepository
            , LegacyAdGroupRepository legacyAdGroupRepository, LegacyAdGroupMigrationService legacyAdGroupMigrationService) {
        super(legacyRepository, null, recentRepository);
        this.legacyAdGroupRepository = legacyAdGroupRepository;
        this.legacyAdGroupMigrationService = legacyAdGroupMigrationService;
    }

    @Override
    protected void migrate(LegacyCampaign legacyCampaign) {
        for(LegacyAdGroup adGroup : legacyAdGroupRepository.findAllByCampaignIdAndDeletedAtIsNull(legacyCampaign.getId())) {
            legacyAdGroupMigrationService.migrate(adGroup.getId());
        }
    }
}
