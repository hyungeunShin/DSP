package com.example.migrationservice.domain.migration.keyword;

import com.example.migrationservice.domain.AggregateType;
import com.example.migrationservice.domain.migration.PageMigration;
import com.example.migrationservice.domain.migration.adgroup.AdGroupPageMigration;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordPageMigration extends PageMigration<KeywordPageMigration> {
    public KeywordPageMigration(Long id, Integer pageNumber, Integer pageSize, Long totalCount) {
        super(id, pageNumber, pageSize, totalCount);
    }

    public static KeywordPageMigration first(Long id, boolean isSuccess, Integer pageSize, Long totalCount) {
        if(isSuccess) {
            return new KeywordPageMigration(id, INIT_PAGE_NUMBER, pageSize, totalCount);
        }

        return new KeywordPageMigration(id, NOT_STARTED_PAGE_NUMBER, pageSize, totalCount);
    }

    @Override
    protected AggregateType aggregateType() {
        return AggregateType.KEYWORD;
    }
}
