package com.example.migrationservice.domain.recent;

import java.time.LocalDateTime;

public interface MigratedEntity {
    LocalDateTime getMigratedAt();
}
