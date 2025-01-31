package com.example.internalmigrationapplication.controller.migration;

import com.example.migrationservice.application.dispatcher.MigrationDispatcher;
import com.example.migrationservice.application.monitor.MigrationMonitor;
import com.example.migrationservice.application.monitor.MigrationMonitorMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/migration/v1")
@RequiredArgsConstructor
public class MigrationController {
    private final MigrationDispatcher dispatcher;
    private final MigrationMonitor monitor;

    @PutMapping("/retry")
    public MigrationRetryResult retry(@RequestBody MigrationRetryRequest dto) {
        boolean result = dispatcher.dispatch(dto.userId(), dto.aggregateId(), dto.aggregateType());
        return new MigrationRetryResult(result);
    }

    @GetMapping("/progress")
    public MigrationMonitorMetrics measure() {
        return monitor.measure();
    }
}
