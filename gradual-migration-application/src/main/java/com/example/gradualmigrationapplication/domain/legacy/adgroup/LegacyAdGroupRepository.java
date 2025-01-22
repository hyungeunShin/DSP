package com.example.gradualmigrationapplication.domain.legacy.adgroup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegacyAdGroupRepository extends JpaRepository<LegacyAdGroup, Long> {
    List<LegacyAdGroup> findAllByCampaignIdAndDeletedAtIsNull(Long campaignId);
}
