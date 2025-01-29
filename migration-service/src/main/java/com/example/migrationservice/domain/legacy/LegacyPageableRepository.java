package com.example.migrationservice.domain.legacy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LegacyPageableRepository<T> extends JpaRepository<T, Long> {
    Page<T> findAllByUserIdAndDeletedAtIsNullOrderById(Long userId, Pageable pageable);
}
