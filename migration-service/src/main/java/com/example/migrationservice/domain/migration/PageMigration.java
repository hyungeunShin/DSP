package com.example.migrationservice.domain.migration;

import com.example.migrationservice.domain.AggregateType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class PageMigration<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {
    public final static Integer PAGE_INCREMENT = 1;
    public final static Integer INIT_PAGE_NUMBER = 0;
    public final static Integer NOT_STARTED_PAGE_NUMBER = INIT_PAGE_NUMBER - PAGE_INCREMENT;

    @Id
    protected Long id;

    protected Integer pageNumber;

    protected Integer pageSize;

    protected Long totalCount;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    public PageMigration(Long id, Integer pageNumber, Integer pageSize, Long totalCount, LocalDateTime createdAt) {
        this.id = id;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        registerEvent(new PageMigrationEvent(aggregateType(), id, isFinished()));
    }

    public PageMigration(Long id, Integer pageNumber, Integer pageSize, Long totalCount) {
        this(id, pageNumber, pageSize, totalCount, LocalDateTime.now());
    }

    protected abstract AggregateType aggregateType();

    public boolean isFinished() {
        return (long) pageSize * nextPageNumber() >= totalCount;
    }

    public Integer nextPageNumber() {
        return pageNumber + PAGE_INCREMENT;
    }

    public void progress(boolean migrationSuccess, Long totalCount) {
        if(migrationSuccess && !isFinished()) {
            this.pageNumber = nextPageNumber();
        }
        this.totalCount = totalCount;
        this.updatedAt = LocalDateTime.now();
        registerEvent(new PageMigrationEvent(aggregateType(), id, isFinished()));
    }

    public boolean isNotEmpty() {
        return this.totalCount != 0;
    }
}
