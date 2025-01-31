package com.example.migrationservice.application.dispatcher;

import com.example.migrationservice.application.ParentPageLegacyMigrationService;
import com.example.migrationservice.application.legacy.adgroup.ParentPageLegacyAdGroupMigrationService;
import com.example.migrationservice.application.legacy.keyword.ParentPageLegacyKeywordMigrationService;
import com.example.migrationservice.domain.AggregateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
//@RequiredArgsConstructor
public class ParentPageMigrationDispatcher {
//    private final PageLegacyAdGroupMigrationService adGroupMigrationService;
//    private final PageLegacyKeywordMigrationService keywordMigrationService;

    private final Map<AggregateType, ParentPageLegacyMigrationService<?, ?, ?, ?>> migrationServiceMap;

    public ParentPageMigrationDispatcher(ParentPageLegacyAdGroupMigrationService adGroupMigrationService,
                                         ParentPageLegacyKeywordMigrationService keywordMigrationService) {
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
        ParentPageLegacyMigrationService<?, ?, ?, ?> service = Optional.ofNullable(migrationServiceMap.get(aggregateType))
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
