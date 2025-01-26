package com.example.migrationservice.application.legacy.adgroup;

import com.example.migrationservice.application.LegacyMigrationService;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroup;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroupRepository;
import com.example.migrationservice.domain.recent.campaign.RecentCampaign;
import com.example.migrationservice.domain.recent.campaign.RecentCampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyAdGroupMigrationService extends LegacyMigrationService<LegacyAdGroup, RecentCampaign> {
    public LegacyAdGroupMigrationService(LegacyAdGroupRepository legacyRepository, LegacyAdGroupConverter converter, RecentCampaignRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
