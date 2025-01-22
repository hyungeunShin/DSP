package com.example.gradualmigrationapplication.application.legacy.keyword;

import com.example.gradualmigrationapplication.application.LegacyConverter;
import com.example.gradualmigrationapplication.domain.legacy.keyword.LegacyKeyword;
import com.example.gradualmigrationapplication.domain.recent.keyword.RecentKeyword;
import org.springframework.stereotype.Component;

@Component
public class LegacyKeywordConverter implements LegacyConverter<LegacyKeyword, RecentKeyword> {
    @Override
    public RecentKeyword convert(LegacyKeyword legacyKeyword) {
        return RecentKeyword.migrated(
                legacyKeyword.getId(), legacyKeyword.getText(), legacyKeyword.getAdGroupId(),
                legacyKeyword.getUserId(), legacyKeyword.getCreatedAt(), legacyKeyword.getDeletedAt()
        );
    }
}
