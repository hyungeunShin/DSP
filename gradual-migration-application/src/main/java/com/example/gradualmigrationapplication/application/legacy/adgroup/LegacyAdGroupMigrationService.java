package com.example.gradualmigrationapplication.application.legacy.adgroup;

import com.example.gradualmigrationapplication.application.LegacyMigrationService;
import com.example.gradualmigrationapplication.domain.legacy.adgroup.LegacyAdGroup;
import com.example.gradualmigrationapplication.domain.legacy.adgroup.LegacyAdGroupRepository;
import com.example.gradualmigrationapplication.domain.recent.campaign.RecentCampaign;
import com.example.gradualmigrationapplication.domain.recent.campaign.RecentCampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyAdGroupMigrationService extends LegacyMigrationService<LegacyAdGroup, RecentCampaign> {
    public LegacyAdGroupMigrationService(LegacyAdGroupRepository legacyRepository, LegacyAdGroupConverter converter, RecentCampaignRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
