package com.example.gradualmigrationapplication.application.legacy.user;

import com.example.gradualmigrationapplication.application.LegacyMigrationService;
import com.example.gradualmigrationapplication.domain.legacy.user.LegacyUser;
import com.example.gradualmigrationapplication.domain.legacy.user.LegacyUserRepository;
import com.example.gradualmigrationapplication.domain.recent.user.RecentUser;
import com.example.gradualmigrationapplication.domain.recent.user.RecentUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LegacyUserMigrationService extends LegacyMigrationService<LegacyUser, RecentUser> {
    public LegacyUserMigrationService(LegacyUserRepository legacyRepository, LegacyUserConverter converter, RecentUserRepository recentRepository) {
        super(legacyRepository, converter, recentRepository);
    }
}
