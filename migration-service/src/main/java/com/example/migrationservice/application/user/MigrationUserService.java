package com.example.migrationservice.application.user;

import com.example.migrationservice.domain.migration.user.MigrationUser;
import com.example.migrationservice.domain.migration.user.MigrationUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MigrationUserService {
    private final MigrationUserRepository repository;

    @Transactional
    public MigrationUser agree(Long userId) {
        repository.findById(userId).ifPresent(user -> {
            throw new AlreadyAgreedException(String.format("User[Id : %d] already agreed", user.getId()));
        });
        return repository.save(MigrationUser.agreed(userId));
    }

    public MigrationUser findById(Long userId) {
        return repository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    public boolean isDisagreed(Long userId) {
        return repository.findById(userId).isEmpty();
    }
}
