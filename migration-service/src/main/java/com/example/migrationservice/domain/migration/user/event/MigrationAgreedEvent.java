package com.example.migrationservice.domain.migration.user.event;

import com.example.migrationservice.domain.migration.user.MigrationUser;

public class MigrationAgreedEvent extends MigrationUserEvent {
    public MigrationAgreedEvent(MigrationUser migrationUser) {
        super(migrationUser);
    }
}
