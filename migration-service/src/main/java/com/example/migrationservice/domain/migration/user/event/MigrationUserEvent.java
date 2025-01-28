package com.example.migrationservice.domain.migration.user.event;

import com.example.migrationservice.domain.migration.user.MigrationUser;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;

public class MigrationUserEvent {
    protected MigrationUser migrationUser;

    public MigrationUserEvent(MigrationUser migrationUser) {
        this.migrationUser = migrationUser;
    }

    public Long getUserId() {
        return migrationUser.getId();
    }

    public MigrationUserStatus getStatus() {
        return migrationUser.getStatus();
    }

    @Override
    public String toString() {
        return "MigrationUserEvent{" +
                "migrationUser=" + migrationUser +
                '}';
    }
}
