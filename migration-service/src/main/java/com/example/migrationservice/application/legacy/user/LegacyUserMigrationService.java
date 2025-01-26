package com.example.migrationservice.application.legacy.user;

import com.example.migrationservice.application.LegacyMigrationService;
import com.example.migrationservice.domain.legacy.user.LegacyUser;
import com.example.migrationservice.domain.legacy.user.LegacyUserRepository;
import com.example.migrationservice.domain.recent.user.RecentUser;
import com.example.migrationservice.domain.recent.user.RecentUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyUserMigrationService extends LegacyMigrationService<LegacyUser, RecentUser> {
    public LegacyUserMigrationService(LegacyUserRepository legacyRepository, LegacyUserConverter converter, RecentUserRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
