package com.example.migrationservice.domain.migration.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MigrationUserStatusTest {
    @Test
    @DisplayName("RETRIED 상태는 next 호출하면 예외")
    void test1() {
        assertThatThrownBy(MigrationUserStatus.RETRIED::next).isInstanceOf(RetriedNeedPrevStatusForNextException.class);
    }
}