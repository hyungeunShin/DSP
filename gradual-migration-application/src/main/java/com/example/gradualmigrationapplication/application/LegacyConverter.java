package com.example.gradualmigrationapplication.application;

import com.example.gradualmigrationapplication.domain.legacy.DeletableEntity;
import com.example.gradualmigrationapplication.domain.recent.MigratedEntity;

public interface LegacyConverter<Legacy extends DeletableEntity, Recent extends MigratedEntity> {
    Recent convert(Legacy legacy);
}
