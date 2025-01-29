package com.example.migrationservice.application.legacy.keyword;

import com.example.migrationservice.application.PageLegacyMigrationService;
import com.example.migrationservice.domain.legacy.keyword.LegacyKeyword;
import com.example.migrationservice.domain.legacy.keyword.LegacyKeywordRepository;
import com.example.migrationservice.domain.migration.keyword.KeywordPageMigration;
import com.example.migrationservice.domain.migration.keyword.KeywordPageMigrationRepository;
import com.example.migrationservice.domain.recent.keyword.RecentKeyword;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageLegacyKeywordMigrationService extends PageLegacyMigrationService<KeywordPageMigration, LegacyKeyword, RecentKeyword> {
    public PageLegacyKeywordMigrationService(KeywordPageMigrationRepository pageMigrationRepository, LegacyKeywordRepository legacyPageableRepository, LegacyKeywordMigrationService legacyMigrationService) {
        super(pageMigrationRepository, legacyPageableRepository, legacyMigrationService);
    }

    @Override
    protected KeywordPageMigration firstPageMigration(Long userId, boolean isSuccess, Page<LegacyKeyword> legacyKeywords) {
        return KeywordPageMigration.first(userId, isSuccess, PAGE_SIZE, legacyKeywords.getTotalElements());
    }
}
