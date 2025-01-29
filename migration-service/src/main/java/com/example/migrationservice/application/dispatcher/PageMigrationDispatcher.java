package com.example.migrationservice.application.dispatcher;

import com.example.migrationservice.application.PageLegacyMigrationService;
import com.example.migrationservice.application.legacy.adgroup.PageLegacyAdGroupMigrationService;
import com.example.migrationservice.application.legacy.keyword.PageLegacyKeywordMigrationService;
import com.example.migrationservice.domain.AggregateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
//@RequiredArgsConstructor
public class PageMigrationDispatcher {
//    private final PageLegacyAdGroupMigrationService adGroupMigrationService;
//    private final PageLegacyKeywordMigrationService keywordMigrationService;

    private final Map<AggregateType, PageLegacyMigrationService<?, ?, ?>> migrationServiceMap;

    public PageMigrationDispatcher(PageLegacyAdGroupMigrationService adGroupMigrationService,
                                   PageLegacyKeywordMigrationService keywordMigrationService) {
        this.migrationServiceMap = Map.of(
                AggregateType.ADGROUP, adGroupMigrationService,
                AggregateType.KEYWORD, keywordMigrationService
        );
    }

    public boolean dispatch(Long userId, AggregateType aggregateType) {
//        PageLegacyMigrationService service = switch(aggregateType) {
//            case ADGROUP -> adGroupMigrationService;
//            case KEYWORD -> keywordMigrationService;
//            default -> throw new PageLegacyMigrationServiceNotFoundException();
//        };
        PageLegacyMigrationService<?, ?, ?> service = Optional.ofNullable(migrationServiceMap.get(aggregateType))
                .orElseThrow(PageLegacyMigrationServiceNotFoundException::new);

        PageMigrationResult result = service.migrate(userId);
        if(result.isSuccess()) {
            log.info("{}", PageMigrationLog.success(result, aggregateType));
        } else {
            log.error("{}", PageMigrationLog.fail(result, aggregateType));
        }
        return result.isSuccess();
    }
}
