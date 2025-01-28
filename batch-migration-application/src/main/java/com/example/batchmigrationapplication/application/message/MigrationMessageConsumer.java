package com.example.batchmigrationapplication.application.message;

import com.example.migrationservice.application.user.MigrationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationMessageConsumer {
    private final MigrationMessageProcessor processor;

    @Bean
    public Consumer<MigrationUserMessage> migrationUserConsumer() {
        //yml에 선언한 binding 명이 메소드 명이어야 함
        return message -> {
            processor.progressMigration(message.userId(), message.status());
            log.info("migration user consumer : {}", message);
        };
    }
}
