package com.example.migrationservice.application.monitor;

import com.example.migrationservice.domain.migration.PageMigration;
import com.example.migrationservice.domain.migration.adgroup.AdGroupPageMigrationRepository;
import com.example.migrationservice.domain.migration.keyword.KeywordPageMigrationRepository;
import com.example.migrationservice.domain.migration.user.MigrationUser;
import com.example.migrationservice.domain.migration.user.MigrationUserRepository;
import com.example.migrationservice.domain.migration.user.MigrationUserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class MigrationMonitor {
    private final MigrationUserRepository userRepository;
    private final AdGroupPageMigrationRepository adGroupPageMigrationRepository;
    private final KeywordPageMigrationRepository keywordPageMigrationRepository;

    public MigrationMonitorMetrics measure() {
        MigrationUsers migrationUsers = MigrationUsers.of(userRepository.findAll());
        PageMigrations adGroupPages = PageMigrations.of(adGroupPageMigrationRepository.findAll());
        PageMigrations keywordPages = PageMigrations.of(keywordPageMigrationRepository.findAll());
        return MigrationMonitorMetrics.from(migrationUsers, adGroupPages, keywordPages);
    }

    public record MigrationUsers(Iterable<MigrationUser> users) {
        public static MigrationUsers of(Iterable<MigrationUser> users) {
            return new MigrationUsers(users);
        }

        public Map<MigrationUserStatus, Long> statusStatistics() {
            return StreamSupport.stream(users.spliterator(), true)
                    .collect(Collectors.groupingBy(MigrationUser::getStatus, Collectors.counting()));
        }
    }

    public record PageMigrations(Iterable<? extends PageMigration<?>> pageMigrations) {
        public static PageMigrations of(Iterable<? extends PageMigration<?>> pageMigrations) {
            return new PageMigrations(pageMigrations);
        }

        public Long migratedCount() {
            return StreamSupport.stream(pageMigrations.spliterator(), true)
                    .filter(PageMigration::isNotEmpty)
                    .map(pageMigration -> {
                        if(pageMigration.isFinished()) {
                            return pageMigration.getTotalCount();
                        }
                        return (long) pageMigration.nextPageNumber() * pageMigration.getPageSize();
                    })
                    .mapToLong(Long::longValue)
                    .sum();
        }

        public Long totalCount() {
            return StreamSupport.stream(pageMigrations.spliterator(), true)
                    .filter(PageMigration::isNotEmpty)
                    .map(PageMigration::getTotalCount)
                    .mapToLong(Long::longValue)
                    .sum();
        }
    }
}
