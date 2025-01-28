package com.example.batchmigrationapplication.application.event;

import com.example.batchmigrationapplication.application.message.MigrationUserMessage;
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
    private static final String OUTPUT_BINDING = "migration-user-out";
    private final StreamBridge streamBridge;

    @TransactionalEventListener
    public void handleMigrationProgressedEvent(MigrationProgressEvent event) {
        log.info("migration progressed event listener : {}", event.toString());
        streamBridge.send(OUTPUT_BINDING,
                MessageBuilder.createMessage(MigrationUserMessage.from(event), new MessageHeaders(Map.of("partitionKey", event.getUserId())))
        );
    }
}
