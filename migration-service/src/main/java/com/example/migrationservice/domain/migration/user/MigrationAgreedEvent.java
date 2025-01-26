package com.example.migrationservice.domain.migration.user;

public class MigrationAgreedEvent extends MigrationUserEvent {
    public MigrationAgreedEvent(MigrationUser migrationUser) {
        super(migrationUser);
    }
}
