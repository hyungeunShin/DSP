package com.example.migrationservice.domain.legacy.campaign;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegacyCampaignRepository extends JpaRepository<LegacyCampaign, Long> {
}
