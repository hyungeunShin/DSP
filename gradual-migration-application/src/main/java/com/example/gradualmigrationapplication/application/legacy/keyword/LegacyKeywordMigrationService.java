package com.example.gradualmigrationapplication.application.legacy.keyword;

import com.example.gradualmigrationapplication.application.LegacyMigrationService;
import com.example.gradualmigrationapplication.domain.legacy.keyword.LegacyKeyword;
import com.example.gradualmigrationapplication.domain.legacy.keyword.LegacyKeywordRepository;
import com.example.gradualmigrationapplication.domain.recent.keyword.RecentKeyword;
import com.example.gradualmigrationapplication.domain.recent.keyword.RecentKeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyKeywordMigrationService extends LegacyMigrationService<LegacyKeyword, RecentKeyword> {
    public LegacyKeywordMigrationService(LegacyKeywordRepository legacyRepository, LegacyKeywordConverter converter, RecentKeywordRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
