package com.example.migrationservice.application.legacy.user;

import com.example.migrationservice.application.LegacyConverter;
import com.example.migrationservice.domain.legacy.user.LegacyUser;
import com.example.migrationservice.domain.recent.user.RecentUser;
import org.springframework.stereotype.Component;

@Component
public class LegacyUserConverter implements LegacyConverter<LegacyUser, RecentUser> {
    @Override
    public RecentUser convert(LegacyUser legacyUser) {
        return RecentUser.migrated(legacyUser.getId(), legacyUser.getName(),
                legacyUser.getCreatedAt(), legacyUser.getUpdatedAt(), legacyUser.getDeletedAt());
    }
}
