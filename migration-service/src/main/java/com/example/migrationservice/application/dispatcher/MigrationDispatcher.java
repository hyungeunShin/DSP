package com.example.migrationservice.application.dispatcher;

import com.example.migrationservice.application.legacy.user.LegacyUserMigrationService;
import com.example.migrationservice.application.MigrationService;
import com.example.migrationservice.application.legacy.adgroup.LegacyAdGroupMigrationService;
import com.example.migrationservice.application.legacy.campaign.LegacyCampaignMigrationService;
import com.example.migrationservice.application.legacy.keyword.LegacyKeywordMigrationService;
import com.example.migrationservice.application.user.MigrationUserService;
import com.example.migrationservice.domain.AggregateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
//@RequiredArgsConstructor
public class MigrationDispatcher {
//    private final LegacyUserMigrationService userMigrationService;
//    private final LegacyCampaignMigrationService campaignMigrationService;
//    private final LegacyAdGroupMigrationService adGroupMigrationService;
//    private final LegacyKeywordMigrationService keywordMigrationService;

    private final MigrationUserService migrationUserService;

    private final Map<AggregateType, MigrationService> migrationServiceMap;

    public MigrationDispatcher(LegacyUserMigrationService userMigrationService,
                               LegacyCampaignMigrationService campaignMigrationService,
                               LegacyAdGroupMigrationService adGroupMigrationService,
                               LegacyKeywordMigrationService keywordMigrationService,
                               MigrationUserService migrationUserService) {
        this.migrationServiceMap = Map.of(
                AggregateType.USER, userMigrationService,
                AggregateType.CAMPAIGN, campaignMigrationService,
                AggregateType.ADGROUP, adGroupMigrationService,
                AggregateType.KEYWORD, keywordMigrationService
        );
        this.migrationUserService = migrationUserService;
    }

    public boolean dispatch(Long userId, Long aggregateId, AggregateType aggregateType) {
//        MigrationService service = switch(aggregateType) {
//            case USER -> userMigrationService;
//            case CAMPAIGN -> campaignMigrationService;
//            case ADGROUP -> adGroupMigrationService;
//            case KEYWORD -> keywordMigrationService;
//        };
        if(migrationUserService.isDisagreed(userId)) {
            return false;
        }

        MigrationService service = migrationServiceMap.get(aggregateType);

        boolean result = service.migrate(aggregateId);

        if(result) {
            log.info("{}", LegacyMigrationLog.success(userId, aggregateType, aggregateId));
        } else {
            log.error("{}", LegacyMigrationLog.fail(userId, aggregateType, aggregateId));
        }

        return result;
    }
}
