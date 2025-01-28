package com.example.batchmigrationapplication.application.message;

import com.example.migrationservice.application.user.MigrationUserService;
import com.example.migrationservice.application.user.StartMigrationFailedException;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationMessageProcessor {
    private final MigrationUserService service;

    public void progressMigration(Long userId, MigrationUserStatus status) {
        switch(status) {
            case AGREED -> {
                try {
                    service.startMigration(userId);
                } catch(StartMigrationFailedException e) {
                    log.error("start migration failed", e);
                }
            }

            case USER_FINISHED, ADGROUP_FINISHED, KEYWORD_FINISHED -> service.progressMigration(userId);
        }
    }
}
