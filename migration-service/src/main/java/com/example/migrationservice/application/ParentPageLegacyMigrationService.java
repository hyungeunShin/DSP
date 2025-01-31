package com.example.migrationservice.application;

import com.example.migrationservice.domain.legacy.DeletableEntity;
import com.example.migrationservice.domain.legacy.LegacyPageableRepository;
import com.example.migrationservice.domain.migration.PageMigration;
import com.example.migrationservice.domain.migration.PageMigrationRepository;
import com.example.migrationservice.domain.recent.MigratedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public abstract class ParentPageLegacyMigrationService<P extends PageMigration<P>, Legacy extends DeletableEntity, Recent extends MigratedEntity, LegacyParent extends DeletableEntity> extends PageLegacyMigrationService<P, Legacy, Recent> {
    public final static Integer PARENT_PAGE_SIZE = 10;
    protected final LegacyPageableRepository<LegacyParent> parentRepository;

    public ParentPageLegacyMigrationService(PageMigrationRepository<P> pageMigrationRepository, LegacyPageableRepository<Legacy> legacyPageableRepository, LegacyMigrationService<Legacy, Recent> legacyMigrationService, LegacyPageableRepository<LegacyParent> parentRepository) {
        super(pageMigrationRepository, legacyPageableRepository, legacyMigrationService);
        this.parentRepository = parentRepository;
    }

    @Override
    protected Page<Legacy> findPage(Long userId, Integer pageNumber) {
        Page<LegacyParent> legacyParentPage = parentRepository.findAllByUserIdAndDeletedAtIsNullOrderById(userId, PageRequest.of(pageNumber, PARENT_PAGE_SIZE));
        List<Long> legacyParentIds = legacyParentPage.stream().map(DeletableEntity::getId).toList();
        List<Legacy> legacies = findAllByParentIdsIn(legacyParentIds);
        return new PageImpl<>(legacies, PageRequest.of(pageNumber, PARENT_PAGE_SIZE), legacyParentPage.getTotalElements());
    }

    protected abstract List<Legacy> findAllByParentIdsIn(List<Long> legacyParentIds);
}
