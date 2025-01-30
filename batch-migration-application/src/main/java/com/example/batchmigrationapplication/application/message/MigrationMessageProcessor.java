package com.example.batchmigrationapplication.application.message;

import com.example.migrationservice.application.dispatcher.PageMigrationDispatcher;
import com.example.migrationservice.application.user.MigrationUserService;
import com.example.migrationservice.application.user.StartMigrationFailedException;
import com.example.migrationservice.domain.AggregateType;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationMessageProcessor {
    private final MigrationUserService service;

    private final PageMigrationDispatcher dispatcher;

    private void progressMigration(Long userId, MigrationUserStatus status) {
        switch(status) {
            case AGREED -> {
                try {
                    service.startMigration(userId);
                } catch(StartMigrationFailedException e) {
                    log.error("start migration failed", e);
                }
            }

            case USER_FINISHED -> dispatch(userId, AggregateType.ADGROUP);
            case ADGROUP_FINISHED -> dispatch(userId, AggregateType.KEYWORD);
            case KEYWORD_FINISHED -> service.progressMigration(userId);
        }
    }

    public void progressMigration(Long userId, MigrationUserStatus status, MigrationUserStatus prevStatus) {
        switch(status) {
            case RETRIED -> progressMigration(userId, prevStatus);
            case AGREED, USER_FINISHED, ADGROUP_FINISHED, KEYWORD_FINISHED -> progressMigration(userId, status);
            default -> log.info("MigrationMessageProcessor userId : {}, status : {}, prevStatus : {}", userId, status, prevStatus);
        }
    }

    public void processPageMigration(Long userId, AggregateType aggregateType, boolean isFinished) {
        if(isFinished) {
            service.progressMigration(userId);
        } else {
            dispatch(userId, aggregateType);
        }
    }

    private void dispatch(Long userId, AggregateType aggregateType) {
        dispatcher.dispatch(userId, aggregateType);
    }
}
