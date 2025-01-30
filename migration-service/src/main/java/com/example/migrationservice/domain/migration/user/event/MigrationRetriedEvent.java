package com.example.migrationservice.domain.migration.user.event;

import com.example.migrationservice.domain.migration.user.MigrationUser;

public class MigrationRetriedEvent extends MigrationUserEvent {
    public MigrationRetriedEvent(MigrationUser migrationUser) {
        super(migrationUser);
    }
}
