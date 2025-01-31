package com.example.migrationservice.domain.legacy.keyword;

import com.example.migrationservice.domain.legacy.LegacyPageableRepository;

import java.util.List;

public interface LegacyKeywordRepository extends LegacyPageableRepository<LegacyKeyword> {
    List<LegacyKeyword> findAllByAdGroupIdInAndDeletedAtIsNull(List<Long> adGroupIds);
}
