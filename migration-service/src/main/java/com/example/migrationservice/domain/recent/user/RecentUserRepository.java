package com.example.migrationservice.domain.recent.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentUserRepository extends JpaRepository<RecentUser, Long> {
}
