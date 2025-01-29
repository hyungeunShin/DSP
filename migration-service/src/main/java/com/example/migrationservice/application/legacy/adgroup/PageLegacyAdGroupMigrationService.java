package com.example.migrationservice.application.legacy.adgroup;

import com.example.migrationservice.application.PageLegacyMigrationService;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroup;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroupRepository;
import com.example.migrationservice.domain.migration.adgroup.AdGroupPageMigration;
import com.example.migrationservice.domain.migration.adgroup.AdGroupPageMigrationRepository;
import com.example.migrationservice.domain.recent.campaign.RecentCampaign;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageLegacyAdGroupMigrationService extends PageLegacyMigrationService<AdGroupPageMigration, LegacyAdGroup, RecentCampaign> {
    public PageLegacyAdGroupMigrationService(AdGroupPageMigrationRepository pageMigrationRepository, LegacyAdGroupRepository legacyPageableRepository, LegacyAdGroupMigrationService legacyMigrationService) {
        super(pageMigrationRepository, legacyPageableRepository, legacyMigrationService);
    }

    @Override
    protected AdGroupPageMigration firstPageMigration(Long userId, boolean isSuccess, Page<LegacyAdGroup> legacyAdGroups) {
        return AdGroupPageMigration.first(userId, isSuccess, PAGE_SIZE, legacyAdGroups.getTotalElements());
    }
}
