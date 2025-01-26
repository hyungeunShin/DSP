package com.example.migrationservice.application.legacy.keyword;

import com.example.migrationservice.application.LegacyConverter;
import com.example.migrationservice.domain.legacy.keyword.LegacyKeyword;
import com.example.migrationservice.domain.recent.keyword.RecentKeyword;
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
