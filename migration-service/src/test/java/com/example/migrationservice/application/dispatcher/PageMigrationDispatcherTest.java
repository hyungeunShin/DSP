package com.example.migrationservice.application.dispatcher;

import com.example.migrationservice.application.legacy.adgroup.PageLegacyAdGroupMigrationService;
import com.example.migrationservice.application.legacy.keyword.PageLegacyKeywordMigrationService;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class PageMigrationDispatcherTest {
    @Mock
    PageLegacyAdGroupMigrationService adGroupMigrationService;

    @Mock
    PageLegacyKeywordMigrationService keywordMigrationService;

    @InjectMocks
    PageMigrationDispatcher dispatcher;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("잘못된 aggregateType이 오면 예외")
    void test1() {
        assertThatThrownBy(() -> dispatcher.dispatch(1L, AggregateType.CAMPAIGN)).isInstanceOf(PageLegacyMigrationServiceNotFoundException.class);
    }

    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    @DisplayName("dispatch한 pageMigrationService의 결과")
    void test2(boolean migrationSuccess) {
        when(adGroupMigrationService.migrate(1L)).thenReturn(new PageMigrationResult(1L, 1, 1, 1L, migrationSuccess));

        boolean result = dispatcher.dispatch(1L, AggregateType.ADGROUP);

        assertThat(result).isEqualTo(migrationSuccess);
    }
}