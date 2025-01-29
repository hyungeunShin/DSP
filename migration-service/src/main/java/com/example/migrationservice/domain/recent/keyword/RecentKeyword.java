package com.example.migrationservice.domain.recent.keyword;

import com.example.migrationservice.domain.recent.MigratedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecentKeyword implements MigratedEntity {
    @Id
    private Long id;

    private String text;

    private Long campaignId;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private LocalDateTime migratedAt;

    public RecentKeyword(Long id, String text, Long campaignId, Long userId, LocalDateTime createdAt, LocalDateTime deletedAt, LocalDateTime migratedAt) {
        this.id = id;
        this.text = text;
        this.campaignId = campaignId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.migratedAt = migratedAt;
    }

    public static RecentKeyword migrated(Long id, String text, Long campaignId, Long userId, LocalDateTime createdAt, LocalDateTime deletedAt) {
        return new RecentKeyword(id, text, campaignId, userId, createdAt, deletedAt, LocalDateTime.now());
    }
}
