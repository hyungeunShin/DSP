package com.example.batchmigrationapplication.application.event;

import com.example.batchmigrationapplication.application.message.MigrationUserMessage;
import com.example.batchmigrationapplication.application.message.PageMigrationMessage;
import com.example.migrationservice.domain.migration.PageMigrationEvent;
import com.example.migrationservice.domain.migration.user.event.MigrationProgressEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationEventListener {
    private static final String MIGRATION_USER_OUT = "migration-user-out";
    private static final String PAGE_MIGRATION_OUT = "page-migration-out";
    private final StreamBridge streamBridge;

    @TransactionalEventListener
    public void handleMigrationProgressedEvent(MigrationProgressEvent event) {
        log.info("migration progressed event listener : {}", event.toString());
        streamBridge.send(MIGRATION_USER_OUT,
                MessageBuilder.createMessage(MigrationUserMessage.from(event), new MessageHeaders(Map.of("partitionKey", event.getUserId())))
        );
    }

    @TransactionalEventListener
    public void handlePageMigrationEvent(PageMigrationEvent event) {
        log.info("page migration event listener : {}", event.toString());
        streamBridge.send(PAGE_MIGRATION_OUT,
                MessageBuilder.createMessage(PageMigrationMessage.from(event), new MessageHeaders(Map.of("partitionKey", event.userId())))
        );
    }
}
