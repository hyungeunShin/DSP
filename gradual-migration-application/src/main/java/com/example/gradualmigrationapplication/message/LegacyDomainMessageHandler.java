package com.example.gradualmigrationapplication.message;

import com.example.migrationservice.application.dispatcher.MigrationDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class LegacyDomainMessageHandler {
    private final MigrationDispatcher dispatcher;

    @Bean
    public Consumer<LegacyDomainMessage> legacyConsumer() {
        return message -> dispatcher.dispatch(message.ownerId(), message.aggregateId(), message.aggregateType());
    }
}
