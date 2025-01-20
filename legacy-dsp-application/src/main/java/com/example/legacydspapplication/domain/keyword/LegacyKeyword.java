package com.example.legacydspapplication.domain.keyword;

import com.example.legacydspapplication.domain.keyword.event.LegacyKeywordCreatedEvent;
import com.example.legacydspapplication.domain.keyword.event.LegacyKeywordDeletedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LegacyKeyword extends AbstractAggregateRoot<LegacyKeyword> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Long adGroupId;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    public LegacyKeyword(String text, Long adGroupId, Long userId, LocalDateTime createdAt) {
        this.text = text;
        this.adGroupId = adGroupId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyKeywordCreatedEvent(this));
    }

    public static LegacyKeyword of(String text, Long adGroupId, Long userId) {
        return new LegacyKeyword(text, adGroupId, userId, LocalDateTime.now());
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        registerEvent(new LegacyKeywordDeletedEvent(this));
    }
}
