package com.example.migrationservice.domain.migration.user.event;

import com.example.migrationservice.domain.migration.user.MigrationUser;

public class MigrationProgressEvent extends MigrationUserEvent {
    public MigrationProgressEvent(MigrationUser migrationUser) {
        super(migrationUser);
    }
}
