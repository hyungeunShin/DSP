package com.example.migrationservice.application.dispatcher;

import com.example.migrationservice.application.legacy.adgroup.LegacyAdGroupMigrationService;
import com.example.migrationservice.application.legacy.campaign.LegacyCampaignMigrationService;
import com.example.migrationservice.application.legacy.keyword.LegacyKeywordMigrationService;
import com.example.migrationservice.application.legacy.user.LegacyUserMigrationService;
import com.example.migrationservice.application.user.MigrationUserService;
import com.example.migrationservice.domain.AggregateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class MigrationDispatcherTest {
    @Mock
    LegacyUserMigrationService userMigrationService;

    @Mock
    LegacyCampaignMigrationService campaignMigrationService;

    @Mock
    LegacyAdGroupMigrationService adGroupMigrationService;

    @Mock
    LegacyKeywordMigrationService keywordMigrationService;

    @Mock
    MigrationUserService migrationUserService;

    @InjectMocks
    MigrationDispatcher dispatcher;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    @DisplayName("사용자가 마이그레이션에 동의하지 않았으면 false 반환")
    void test1(boolean migrationSuccess) {
        when(migrationUserService.isDisagreed(1L)).thenReturn(true);
        when(adGroupMigrationService.migrate(1L)).thenReturn(migrationSuccess);

        boolean result = dispatcher.dispatch(1L, 1L, AggregateType.ADGROUP);

        assertThat(result).isFalse();
    }

    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    @DisplayName("사용자가 마이그레이션에 동의 했으면 마이그레이션 결과 반환")
    void test2(boolean migrationSuccess) {
        when(migrationUserService.isDisagreed(1L)).thenReturn(false);
        when(adGroupMigrationService.migrate(1L)).thenReturn(migrationSuccess);

        boolean result = dispatcher.dispatch(1L, 1L, AggregateType.ADGROUP);

        assertThat(result).isEqualTo(migrationSuccess);
    }
}