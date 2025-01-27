package com.example.internalmigrationapplication.controller.migration;

import com.example.migrationservice.application.dispatcher.MigrationDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migration/v1")
@RequiredArgsConstructor
public class MigrationController {
    private final MigrationDispatcher dispatcher;

    @PutMapping("/retry")
    public MigrationRetryResult retry(@RequestBody MigrationRetryRequest dto) {
        boolean result = dispatcher.dispatch(dto.userId(), dto.aggregateId(), dto.aggregateType());
        return new MigrationRetryResult(result);
    }
}
