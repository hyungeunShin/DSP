package com.example.migrationservice.application;

import com.example.migrationservice.application.dispatcher.PageMigrationResult;
import com.example.migrationservice.domain.legacy.DeletableEntity;
import com.example.migrationservice.domain.legacy.LegacyPageableRepository;
import com.example.migrationservice.domain.migration.PageMigration;
import com.example.migrationservice.domain.migration.PageMigrationRepository;
import com.example.migrationservice.domain.recent.MigratedEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public abstract class PageLegacyMigrationService<P extends PageMigration<P>, Legacy extends DeletableEntity, Recent extends MigratedEntity> {
    public final static Integer PAGE_SIZE = 1;
    protected final PageMigrationRepository<P> pageMigrationRepository;
    protected final LegacyPageableRepository<Legacy> legacyPageableRepository;
    protected final LegacyMigrationService<Legacy, Recent> legacyMigrationService;

    @Transactional
    public PageMigrationResult migrate(Long userId) {
        return pageMigrationRepository.findById(userId)
                .map(this::migrateNextPage)
                .orElseGet(() -> startPageMigration(userId));
    }

    private PageMigrationResult migrateNextPage(P pageMigration) {
        Integer pageNumber = pageMigration.nextPageNumber();
        Page<Legacy> legacyPage = legacyPageableRepository.findAllByUserIdAndDeletedAtIsNullOrderById(pageMigration.getId(), PageRequest.of(pageNumber, PAGE_SIZE));
        boolean isSuccess = legacyMigrationService.migrate(legacyPage.toList());
        pageMigration.progress(isSuccess, legacyPage.getTotalElements());
        pageMigrationRepository.save(pageMigration);
        return new PageMigrationResult(pageMigration.getId(), pageMigration.getPageNumber(), legacyPage.getTotalPages(), pageMigration.getTotalCount(), isSuccess);
    }

    private PageMigrationResult startPageMigration(Long userId) {
        Integer pageNumber = PageMigration.INIT_PAGE_NUMBER;
        Page<Legacy> legacyPage = legacyPageableRepository.findAllByUserIdAndDeletedAtIsNullOrderById(userId, PageRequest.of(pageNumber, PAGE_SIZE));
        boolean isSuccess = legacyMigrationService.migrate(legacyPage.toList());
        P pageMigration = firstPageMigration(userId, isSuccess, legacyPage);
        pageMigrationRepository.save(pageMigration);
        return new PageMigrationResult(userId, pageMigration.getPageNumber(), legacyPage.getTotalPages(), pageMigration.getTotalCount(), isSuccess);
    }

    protected abstract P firstPageMigration(Long userId, boolean isSuccess, Page<Legacy> legacyPage);
}
