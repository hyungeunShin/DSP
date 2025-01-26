package com.example.migrationservice.domain.migration.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MigrationUserRepository extends JpaRepository<MigrationUser, Long> {
}
