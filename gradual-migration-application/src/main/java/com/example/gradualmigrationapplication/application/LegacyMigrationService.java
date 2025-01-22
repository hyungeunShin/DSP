package com.example.gradualmigrationapplication.application;

import com.example.gradualmigrationapplication.domain.legacy.DeletableEntity;
import com.example.gradualmigrationapplication.domain.recent.MigratedEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

@Slf4j
public abstract class LegacyMigrationService<Legacy extends DeletableEntity, Recent extends MigratedEntity> implements MigrationService {
    protected CrudRepository<Legacy, Long> legacyRepository;
    protected LegacyConverter<Legacy, Recent> converter;
    protected CrudRepository<Recent, Long> recentRepository;

    public LegacyMigrationService(CrudRepository<Legacy, Long> legacyRepository, LegacyConverter<Legacy, Recent> converter, CrudRepository<Recent, Long> recentRepository) {
        this.converter = converter;
        this.legacyRepository = legacyRepository;
        this.recentRepository = recentRepository;
    }

    @Override
    public boolean migrate(Long id) {
        try {
            migrate(legacyRepository.findById(id).orElseThrow(EntityNotFoundException::new));
            return true;
        } catch(RuntimeException e) {
            log.error("migration error", e);
            return false;
        }
    }

    protected void migrate(Legacy legacy) {
        if(legacy.isDeleted()) {
            recentRepository.findById(legacy.getId()).ifPresent(recent -> recentRepository.delete(recent));
        } else {
            Recent recent = converter.convert(legacy);
            recentRepository.save(recent);
        }
    }
}
