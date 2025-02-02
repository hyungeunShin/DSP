package com.example.migrationservice.application.user;

import com.example.migrationservice.application.legacy.user.LegacyUserMigrationService;
import com.example.migrationservice.domain.migration.user.MigrationUser;
import com.example.migrationservice.domain.migration.user.MigrationUserRepository;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class MigrationUserServiceTest {
    @Mock
    MigrationUserRepository repository;

    @Mock
    LegacyUserMigrationService legacyUserMigrationService;

    @InjectMocks
    MigrationUserService service;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("사용자가 마이그레이션에 동의")
    void test1() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        LocalDateTime before = LocalDateTime.now();
        MigrationUser result = service.agree(1L);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(result.getId()).isEqualTo(1L),
                () -> assertThat(result.getStatus()).isEqualTo(MigrationUserStatus.AGREED),
                () -> assertThat(result.getAgreedAt()).isAfter(before).isBefore(after),
                () -> assertThat(result.getUpdatedAt()).isAfter(before).isBefore(after)
        );
    }

    @Test
    @DisplayName("사용자가 이미 마이그레이션에 동의했으면 예외")
    void test2() {
        when(repository.findById(1L)).thenReturn(Optional.of(MigrationUser.agreed(1L)));

        assertThatThrownBy(() -> service.agree(1L)).isInstanceOf(AlreadyAgreedException.class);
    }

    @Test
    @DisplayName("존재하지 않으면 예외")
    void test3() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(1L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("동의하지 않았으면 true 반환")
    void test4() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        boolean result = service.isDisagreed(1L);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("동의 했으면 false 반환")
    void test5() {
        when(repository.findById(1L)).thenReturn(Optional.of(MigrationUser.agreed(1L)));

        boolean result = service.isDisagreed(1L);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("마이그레이션 시작하고 사용자 마이그레이션이 성공하면 사용자 상태 업데이트")
    void test6() throws StartMigrationFailedException {
        when(repository.findById(1L)).thenReturn(Optional.of(MigrationUser.agreed(1L)));
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(legacyUserMigrationService.migrate(anyLong())).thenReturn(true);

        MigrationUser user = service.startMigration(1L);

        assertThat(user.getStatus()).isEqualTo(MigrationUserStatus.USER_FINISHED);
    }

    @Test
    @DisplayName("마이그레이션 시작하고 사용자 마이그레이션이 실패하면 예외")
    void test7() {
        when(legacyUserMigrationService.migrate(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> service.startMigration(1L)).isInstanceOf(StartMigrationFailedException.class);
    }

    @Test
    @DisplayName("마이그레이션 진행 중에 사용자 상태 업데이트")
    void test8() {
        MigrationUser user = MigrationUser.agreed(1L);
        user.progressMigration();

        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        MigrationUser result = service.progressMigration(1L);

        assertThat(result.getStatus()).isEqualTo(MigrationUserStatus.ADGROUP_FINISHED);
    }
}