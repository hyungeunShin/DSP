package com.example.migrationservice.application;

import com.example.migrationservice.application.dispatcher.PageMigrationResult;
import com.example.migrationservice.domain.legacy.DeletableEntity;
import com.example.migrationservice.domain.legacy.LegacyPageableRepository;
import com.example.migrationservice.domain.migration.PageMigration;
import com.example.migrationservice.domain.migration.PageMigrationRepository;
import com.example.migrationservice.domain.migration.PageMigrationTestable;
import com.example.migrationservice.domain.recent.MigratedEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.migrationservice.application.PageLegacyMigrationService.PAGE_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class PageLegacyMigrationServiceTest {
    @Mock
    PageMigrationRepository<PageMigrationTestable> pageMigrationRepository;

    @Mock
    LegacyPageableRepository<DeletableEntity> legacyPageableRepository;

    @Mock
    LegacyMigrationService<DeletableEntity, MigratedEntity> legacyMigrationService;

    PageLegacyMigrationService<PageMigrationTestable, DeletableEntity, MigratedEntity> service;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        service = new PageLegacyMigrationService<>(pageMigrationRepository, legacyPageableRepository, legacyMigrationService) {
            @Override
            protected PageMigrationTestable firstPageMigration(Long userId, boolean isSuccess, Page<DeletableEntity> legacyPage) {
                if(isSuccess) {
                    return new PageMigrationTestable(userId, PageMigration.INIT_PAGE_NUMBER, PAGE_SIZE, legacyPage.getTotalElements());
                } else {
                    return new PageMigrationTestable(userId, PageMigration.NOT_STARTED_PAGE_NUMBER, PAGE_SIZE, legacyPage.getTotalElements());
                }
            }
        };
    }

    @Test
    @DisplayName("pageMigration이 시작이고 마이그레이션이 성공이면 INIT_PAGE_NUMBER로 pageMigration 저장")
    void test1() {
        when(pageMigrationRepository.findById(any())).thenReturn(Optional.empty());
        when(legacyPageableRepository.findAllByUserIdAndDeletedAtIsNullOrderById(any(), any())).thenReturn(new PageImpl<>(List.of(legacyTestable(1L))));
        when(legacyMigrationService.migrate(anyList())).thenReturn(true);
        when(pageMigrationRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PageMigrationResult result = service.migrate(1L);

        assertThat(result).isEqualTo(new PageMigrationResult(1L, PageMigration.INIT_PAGE_NUMBER, 1, 1L, true));
    }

    @Test
    @DisplayName("pageMigration이 시작이고 마이그레이션이 실패하면 NOT_STARTED_PAGE_NUMBER로 pageMigration 저장")
    void test2() {
        when(pageMigrationRepository.findById(any())).thenReturn(Optional.empty());
        when(legacyPageableRepository.findAllByUserIdAndDeletedAtIsNullOrderById(any(), any())).thenReturn(new PageImpl<>(List.of(legacyTestable(1L))));
        when(legacyMigrationService.migrate(anyList())).thenReturn(false);
        when(pageMigrationRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PageMigrationResult result = service.migrate(1L);

        assertThat(result).isEqualTo(new PageMigrationResult(1L, PageMigration.NOT_STARTED_PAGE_NUMBER, 1, 1L, false));
    }
    
    @Test
    @DisplayName("pageMigration이 두번째 페이지이고 마이그레이션이 성공하면 다음 페이지로 저장")
    void test3() {
        when(pageMigrationRepository.findById(any())).thenReturn(Optional.of(new PageMigrationTestable(1L, PageMigration.INIT_PAGE_NUMBER, PAGE_SIZE, PAGE_SIZE * 3L)));
        when(legacyPageableRepository.findAllByUserIdAndDeletedAtIsNullOrderById(any(), any()))
                .thenReturn(new PageImpl<>(List.of(legacyTestable(1L)), PageRequest.of(PageMigration.INIT_PAGE_NUMBER + PageMigration.PAGE_INCREMENT, PAGE_SIZE), PAGE_SIZE * 3L));
        when(legacyMigrationService.migrate(anyList())).thenReturn(true);
        when(pageMigrationRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PageMigrationResult result = service.migrate(1L);

        assertThat(result).isEqualTo(new PageMigrationResult(1L, PageMigration.INIT_PAGE_NUMBER + PageMigration.PAGE_INCREMENT, 3, PAGE_SIZE * 3L, true));
    }

    @Test
    @DisplayName("pageMigration이 두번째 페이지이고 마이그레이션이 실패하면 기존 페이지로 저장")
    void test4() {
        when(pageMigrationRepository.findById(any())).thenReturn(Optional.of(new PageMigrationTestable(1L, PageMigration.INIT_PAGE_NUMBER, PAGE_SIZE, PAGE_SIZE * 3L)));
        when(legacyPageableRepository.findAllByUserIdAndDeletedAtIsNullOrderById(any(), any()))
                .thenReturn(new PageImpl<>(List.of(legacyTestable(1L)), PageRequest.of(PageMigration.INIT_PAGE_NUMBER + PageMigration.PAGE_INCREMENT, PAGE_SIZE), PAGE_SIZE * 3L));
        when(legacyMigrationService.migrate(anyList())).thenReturn(false);
        when(pageMigrationRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PageMigrationResult result = service.migrate(1L);

        assertThat(result).isEqualTo(new PageMigrationResult(1L, PageMigration.INIT_PAGE_NUMBER, 3, PAGE_SIZE * 3L, false));
    }

    private DeletableEntity legacyTestable(Long id) {
        return new DeletableEntity() {
            @Override
            public LocalDateTime getDeletedAt() {
                return null;
            }

            @Override
            public Long getId() {
                return id;
            }
        };
    }
}