package com.example.gradualmigrationapplication.domain.recent.keyword;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecentKeyword {
    @Id
    private Long id;

    private String text;

    private Long campaignId;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private LocalDateTime migratedAt;
}
