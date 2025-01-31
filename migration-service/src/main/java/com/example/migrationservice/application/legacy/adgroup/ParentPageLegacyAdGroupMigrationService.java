package com.example.migrationservice.application.legacy.adgroup;

import com.example.migrationservice.application.ParentPageLegacyMigrationService;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroup;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroupRepository;
import com.example.migrationservice.domain.legacy.campaign.LegacyCampaign;
import com.example.migrationservice.domain.legacy.campaign.LegacyCampaignRepository;
import com.example.migrationservice.domain.migration.adgroup.AdGroupPageMigration;
import com.example.migrationservice.domain.migration.adgroup.AdGroupPageMigrationRepository;
import com.example.migrationservice.domain.recent.campaign.RecentCampaign;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentPageLegacyAdGroupMigrationService extends ParentPageLegacyMigrationService<AdGroupPageMigration, LegacyAdGroup, RecentCampaign, LegacyCampaign> {
    public ParentPageLegacyAdGroupMigrationService(
            AdGroupPageMigrationRepository pageMigrationRepository,
            LegacyAdGroupRepository legacyPageableRepository,
            LegacyAdGroupMigrationService legacyMigrationService,
            LegacyCampaignRepository parentRepository) {
        super(pageMigrationRepository, legacyPageableRepository, legacyMigrationService, parentRepository);
    }

    @Override
    protected AdGroupPageMigration firstPageMigration(Long userId, boolean isSuccess, Page<LegacyAdGroup> legacyAdGroups) {
        return AdGroupPageMigration.first(userId, isSuccess, PARENT_PAGE_SIZE, legacyAdGroups.getTotalElements());
    }

    @Override
    protected List<LegacyAdGroup> findAllByParentIdsIn(List<Long> legacyParentIds) {
        return ((LegacyAdGroupRepository) legacyPageableRepository).findAllByCampaignIdInAndDeletedAtIsNull(legacyParentIds);
    }
}
