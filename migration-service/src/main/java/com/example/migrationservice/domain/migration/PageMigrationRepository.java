package com.example.migrationservice.domain.migration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PageMigrationRepository<T extends PageMigration<T>> extends JpaRepository<T, Long> {
}
