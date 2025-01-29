package com.example.migrationservice.domain.migration;

import com.example.migrationservice.domain.AggregateType;

public class PageMigrationTestable extends PageMigration<PageMigrationTestable> {
    public PageMigrationTestable(Long id, Integer pageNumber, Integer pageSize, Long totalCount) {
        super(id, pageNumber, pageSize, totalCount);
    }

    @Override
    protected AggregateType aggregateType() {
        return AggregateType.ADGROUP;
    }
}
