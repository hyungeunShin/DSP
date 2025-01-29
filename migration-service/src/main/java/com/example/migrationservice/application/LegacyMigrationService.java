package com.example.migrationservice.application;

import com.example.migrationservice.domain.legacy.DeletableEntity;
import com.example.migrationservice.domain.recent.MigratedEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean migrate(List<Legacy> legacies) {
        try {
            List<Recent> recents = legacies.stream().map(legacy -> converter.convert(legacy)).toList();
            log.info("@@@@@@@@@@@@@@@@@ : {}", recents);
            recentRepository.saveAll(recents);
            return true;
        } catch(RuntimeException e) {
            log.error("list migration error", e);
            return false;
        }
    }
}
