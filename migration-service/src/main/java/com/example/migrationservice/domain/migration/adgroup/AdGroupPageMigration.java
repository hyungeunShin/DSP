package com.example.migrationservice.domain.migration.adgroup;

import com.example.migrationservice.domain.AggregateType;
import com.example.migrationservice.domain.migration.PageMigration;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdGroupPageMigration extends PageMigration<AdGroupPageMigration> {
    public AdGroupPageMigration(Long id, Integer pageNumber, Integer pageSize, Long totalCount) {
        super(id, pageNumber, pageSize, totalCount);
    }

    public static AdGroupPageMigration first(Long id, boolean isSuccess, Integer pageSize, Long totalCount) {
        if(isSuccess) {
            return new AdGroupPageMigration(id, INIT_PAGE_NUMBER, pageSize, totalCount);
        }

        return new AdGroupPageMigration(id, NOT_STARTED_PAGE_NUMBER, pageSize, totalCount);
    }

    @Override
    protected AggregateType aggregateType() {
        return AggregateType.ADGROUP;
    }
}
