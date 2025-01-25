package com.example.gradualmigrationapplication.application.dispatcher;

import com.example.gradualmigrationapplication.application.MigrationService;
import com.example.gradualmigrationapplication.application.legacy.adgroup.LegacyAdGroupMigrationService;
import com.example.gradualmigrationapplication.application.legacy.campaign.LegacyCampaignMigrationService;
import com.example.gradualmigrationapplication.application.legacy.keyword.LegacyKeywordMigrationService;
import com.example.gradualmigrationapplication.application.legacy.user.LegacyUserMigrationService;
import com.example.gradualmigrationapplication.domain.AggregateType;
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

    private final Map<AggregateType, MigrationService> migrationServiceMap;

    public MigrationDispatcher(LegacyUserMigrationService userMigrationService,
                               LegacyCampaignMigrationService campaignMigrationService,
                               LegacyAdGroupMigrationService adGroupMigrationService,
                               LegacyKeywordMigrationService keywordMigrationService) {
        this.migrationServiceMap = Map.of(
                AggregateType.USER, userMigrationService,
                AggregateType.CAMPAIGN, campaignMigrationService,
                AggregateType.ADGROUP, adGroupMigrationService,
                AggregateType.KEYWORD, keywordMigrationService
        );
    }

    public boolean dispatch(Long aggregateId, AggregateType aggregateType) {
//        MigrationService service = switch(aggregateType) {
//            case USER -> userMigrationService;
//            case CAMPAIGN -> campaignMigrationService;
//            case ADGROUP -> adGroupMigrationService;
//            case KEYWORD -> keywordMigrationService;
//        };
        MigrationService service = migrationServiceMap.get(aggregateType);

        boolean result = service.migrate(aggregateId);

        if(result) {
            log.info("{}", LegacyMigrationLog.success(aggregateType, aggregateId));
        } else {
            log.error("{}", LegacyMigrationLog.fail(aggregateType, aggregateId));
        }

        return result;
    }
}
