package com.example.migrationservice.domain.migration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PageMigrationTest {
    @ValueSource(ints = {0, 1, 7, 8})
    @ParameterizedTest
    @DisplayName("아직 끝나지 않음")
    void test1(int pageNumber) {
        PageMigration<PageMigrationTestable> pageMigration = new PageMigrationTestable(1L, pageNumber, 10, 100L);

        boolean finished = pageMigration.isFinished();

        assertThat(finished).isFalse();
    }

    @ValueSource(ints = {9, 10, 11})
    @ParameterizedTest
    @DisplayName("끝남")
    void test2(int pageNumber) {
        PageMigration<PageMigrationTestable> pageMigration = new PageMigrationTestable(1L, pageNumber, 10, 100L);

        boolean finished = pageMigration.isFinished();

        assertThat(finished).isTrue();
    }

    @ValueSource(ints = {0, 1, 7, 8})
    @ParameterizedTest
    void test3(int pageNumber) {
        PageMigration<PageMigrationTestable> pageMigration = new PageMigrationTestable(1L, pageNumber, 10, 100L);

        Integer nextPageNumber = pageMigration.nextPageNumber();

        assertThat(nextPageNumber).isEqualTo(pageNumber + PageMigration.PAGE_INCREMENT);
    }

    @Test
    @DisplayName("progress 성공")
    void test4() throws InterruptedException {
        PageMigration<PageMigrationTestable> pageMigration = new PageMigrationTestable(1L, PageMigration.INIT_PAGE_NUMBER, 10, 100L);

        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(100);
        pageMigration.progress(true, 110L);
        Thread.sleep(100);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(pageMigration.pageNumber).isEqualTo(PageMigration.INIT_PAGE_NUMBER + PageMigration.PAGE_INCREMENT),
                () -> assertThat(pageMigration.updatedAt).isAfter(before).isBefore(after),
                () -> assertThat(pageMigration.totalCount).isEqualTo(110L)
        );
    }

    @Test
    @DisplayName("progress 실패")
    void test5() throws InterruptedException {
        PageMigration<PageMigrationTestable> pageMigration = new PageMigrationTestable(1L, PageMigration.INIT_PAGE_NUMBER, 10, 100L);

        LocalDateTime before = LocalDateTime.now();
        Thread.sleep(100);
        pageMigration.progress(false, 110L);
        Thread.sleep(100);
        LocalDateTime after = LocalDateTime.now();

        assertAll(
                () -> assertThat(pageMigration.pageNumber).isEqualTo(PageMigration.INIT_PAGE_NUMBER),
                () -> assertThat(pageMigration.updatedAt).isAfter(before).isBefore(after),
                () -> assertThat(pageMigration.totalCount).isEqualTo(110L)
        );
    }

    @Test
    @DisplayName("마이그레이션을 성공했어도 이미 완료 상태라면 pageNumber를 증가시키지 않음")
    void test6() {
        PageMigration<PageMigrationTestable> pageMigration1 = new PageMigrationTestable(1L, 9, 10, 100L);
        PageMigration<PageMigrationTestable> pageMigration2 = new PageMigrationTestable(1L, 0, 10, 10L);

        pageMigration1.progress(true, 100L);
        pageMigration2.progress(true, 100L);

        assertAll(
                () -> assertThat(pageMigration1.pageNumber).isEqualTo(9),
                () -> assertThat(pageMigration2.pageNumber).isEqualTo(0)
        );
    }
}