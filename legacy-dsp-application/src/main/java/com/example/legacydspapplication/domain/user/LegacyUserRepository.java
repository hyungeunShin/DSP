package com.example.legacydspapplication.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegacyUserRepository extends JpaRepository<LegacyUser, Long> {
}
