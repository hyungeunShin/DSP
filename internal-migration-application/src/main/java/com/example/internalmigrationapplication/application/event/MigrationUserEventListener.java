package com.example.internalmigrationapplication.application.event;

import com.example.migrationservice.domain.migration.user.event.MigrationAgreedEvent;
import com.example.migrationservice.domain.migration.user.event.MigrationRetriedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MigrationUserEventListener {
    private static final String OUTPUT_BINDING = "migration-user-out";
    private final StreamBridge streamBridge;

    @TransactionalEventListener
    public void handleAgreedEvent(MigrationAgreedEvent event) {
        streamBridge.send(OUTPUT_BINDING,
                MessageBuilder.createMessage(MigrationUserMessage.from(event), new MessageHeaders(Map.of("partitionKey", event.getUserId())))
        );
    }

    @TransactionalEventListener
    public void handleRetriedEvent(MigrationRetriedEvent event) {
        streamBridge.send(OUTPUT_BINDING,
                MessageBuilder.createMessage(MigrationUserMessage.from(event), new MessageHeaders(Map.of("partitionKey", event.getUserId())))
        );
    }
}
