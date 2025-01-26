package com.example.migrationservice.application;

import com.example.migrationservice.domain.legacy.DeletableEntity;
import com.example.migrationservice.domain.recent.MigratedEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LegacyMigrationServiceTest {
    @Mock
    LegacyConverter<DeletableEntity, MigratedEntity> converter;

    @Mock
    CrudRepository<DeletableEntity, Long> legacyRepository;

    @Mock
    CrudRepository<MigratedEntity, Long> recentRepository;

    LegacyMigrationService<?, ?> service;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        service = new LegacyMigrationService<>(legacyRepository, converter, recentRepository) {
            @Override
            public boolean migrate(Long id) {
                return super.migrate(id);
            }
        };
    }

    @Test
    @DisplayName("레거시 도메인 조회 시 데이터 없으면 마이그레이션 실패")
    void test1() {
        when(legacyRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = service.migrate(1L);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("레거시 도메인 조회 시 데이터 있으면 마이그레이션 성공")
    void test2() {
        when(legacyRepository.findById(1L)).thenReturn(Optional.of(new DeletableEntity() {
            @Override
            public LocalDateTime getDeletedAt() {
                return null;
            }

            @Override
            public Long getId() {
                return 1L;
            }
        }));
        when(converter.convert(any())).thenReturn(new MigratedEntity() {
            @Override
            public LocalDateTime getMigratedAt() {
                return LocalDateTime.now();
            }
        });
        when(recentRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        boolean result = service.migrate(1L);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("레거시 도메인이 삭제되었고 마이그레이션이 되어 있다면 삭제")
    void test3() {
        when(legacyRepository.findById(1L)).thenReturn(Optional.of(new DeletableEntity() {
            @Override
            public LocalDateTime getDeletedAt() {
                return LocalDateTime.now();
            }

            @Override
            public Long getId() {
                return 1L;
            }
        }));
        when(recentRepository.findById(1L)).thenReturn(Optional.of(() -> LocalDateTime.now().minusMinutes(10)));

        boolean result = service.migrate(1L);

        assertAll(
                () -> assertThat(result).isTrue(),
                () -> verify(recentRepository, times(1)).delete(any())
        );
    }

    @Test
    @DisplayName("마이그레이션 중에 에러가 발생하면 실패")
    void test4() {
        service = new LegacyMigrationService<>(legacyRepository, converter, recentRepository) {
            @Override
            public void migrate(DeletableEntity entity) {
                throw new RuntimeException();
            }
        };

        boolean result = service.migrate(1L);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("조회된 레거시를 다른 로직으로 override 해서 마이그레이션 가능")
    void test5() {
        when(legacyRepository.findById(1L)).thenReturn(Optional.of(new DeletableEntity() {
            @Override
            public LocalDateTime getDeletedAt() {
                return null;
            }

            @Override
            public Long getId() {
                return 1L;
            }
        }));

        service = new LegacyMigrationService<>(legacyRepository, converter, recentRepository) {
            @Override
            public void migrate(DeletableEntity entity) {

            }
        };

        boolean result = service.migrate(1L);

        assertAll(
                () -> assertThat(result).isTrue(),
                () -> verify(recentRepository, times(0)).findById(any()),
                () -> verify(converter, times(0)).convert(any()),
                () -> verify(recentRepository, times(0)).save(any())
        );
    }
}