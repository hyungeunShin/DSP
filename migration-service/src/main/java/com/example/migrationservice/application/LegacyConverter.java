package com.example.migrationservice.application;

import com.example.migrationservice.domain.legacy.DeletableEntity;
import com.example.migrationservice.domain.recent.MigratedEntity;

public interface LegacyConverter<Legacy extends DeletableEntity, Recent extends MigratedEntity> {
    Recent convert(Legacy legacy);
}
