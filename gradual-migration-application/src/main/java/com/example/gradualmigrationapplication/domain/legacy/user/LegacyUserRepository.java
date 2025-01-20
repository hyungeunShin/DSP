package com.example.gradualmigrationapplication.domain.legacy.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegacyUserRepository extends JpaRepository<LegacyUser, Long> {
}
