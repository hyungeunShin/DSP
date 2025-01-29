package com.example.migrationservice.application.dispatcher;

public record PageMigrationResult(Long userId, Integer pageNumber, Integer totalPage, Long totalCount, boolean isSuccess) {
}
