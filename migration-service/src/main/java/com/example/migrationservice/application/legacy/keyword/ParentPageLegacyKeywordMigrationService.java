package com.example.migrationservice.application.legacy.keyword;

import com.example.migrationservice.application.ParentPageLegacyMigrationService;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroup;
import com.example.migrationservice.domain.legacy.adgroup.LegacyAdGroupRepository;
import com.example.migrationservice.domain.legacy.keyword.LegacyKeyword;
import com.example.migrationservice.domain.legacy.keyword.LegacyKeywordRepository;
import com.example.migrationservice.domain.migration.keyword.KeywordPageMigration;
import com.example.migrationservice.domain.migration.keyword.KeywordPageMigrationRepository;
import com.example.migrationservice.domain.recent.keyword.RecentKeyword;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentPageLegacyKeywordMigrationService extends ParentPageLegacyMigrationService<KeywordPageMigration, LegacyKeyword, RecentKeyword, LegacyAdGroup> {
    public ParentPageLegacyKeywordMigrationService(
            KeywordPageMigrationRepository pageMigrationRepository,
            LegacyKeywordRepository legacyPageableRepository,
            LegacyKeywordMigrationService legacyMigrationService,
            LegacyAdGroupRepository parentRepository) {
        super(pageMigrationRepository, legacyPageableRepository, legacyMigrationService, parentRepository);
    }

    @Override
    protected KeywordPageMigration firstPageMigration(Long userId, boolean isSuccess, Page<LegacyKeyword> legacyKeywords) {
        return KeywordPageMigration.first(userId, isSuccess, PARENT_PAGE_SIZE, legacyKeywords.getTotalElements());
    }

    @Override
    protected List<LegacyKeyword> findAllByParentIdsIn(List<Long> legacyParentIds) {
        return ((LegacyKeywordRepository) legacyPageableRepository).findAllByAdGroupIdInAndDeletedAtIsNull(legacyParentIds);
    }
}
