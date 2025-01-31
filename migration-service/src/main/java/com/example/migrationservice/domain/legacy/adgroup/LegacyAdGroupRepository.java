package com.example.migrationservice.domain.legacy.adgroup;

import com.example.migrationservice.domain.legacy.LegacyPageableRepository;

import java.util.List;

public interface LegacyAdGroupRepository extends LegacyPageableRepository<LegacyAdGroup> {
    List<LegacyAdGroup> findAllByCampaignIdAndDeletedAtIsNull(Long campaignId);
    List<LegacyAdGroup> findAllByCampaignIdInAndDeletedAtIsNull(List<Long> campaignIds);
}
