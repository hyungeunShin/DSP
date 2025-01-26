package com.example.migrationservice.application.legacy.keyword;

import com.example.migrationservice.application.LegacyMigrationService;
import com.example.migrationservice.domain.legacy.keyword.LegacyKeyword;
import com.example.migrationservice.domain.legacy.keyword.LegacyKeywordRepository;
import com.example.migrationservice.domain.recent.keyword.RecentKeyword;
import com.example.migrationservice.domain.recent.keyword.RecentKeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyKeywordMigrationService extends LegacyMigrationService<LegacyKeyword, RecentKeyword> {
    public LegacyKeywordMigrationService(LegacyKeywordRepository legacyRepository, LegacyKeywordConverter converter, RecentKeywordRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
