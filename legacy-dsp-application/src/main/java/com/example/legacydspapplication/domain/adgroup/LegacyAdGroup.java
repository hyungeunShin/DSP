package com.example.legacydspapplication.domain.adgroup;

import com.example.legacydspapplication.domain.adgroup.event.LegacyAdGroupCreatedEvent;
import com.example.legacydspapplication.domain.adgroup.event.LegacyAdGroupDeletedEvent;
import com.example.legacydspapplication.domain.adgroup.event.LegacyAdGroupLinkUrlUpdatedEvent;
import com.example.legacydspapplication.domain.adgroup.event.LegacyAdGroupNameUpdatedEvent;
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
public class LegacyAdGroup extends AbstractAggregateRoot<LegacyAdGroup> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long userId;

    private Long campaignId;

    private String linkUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public LegacyAdGroup(String name, Long userId, Long campaignId, String linkUrl, LocalDateTime createdAt) {
        this.name = name;
        this.userId = userId;
        this.campaignId = campaignId;
        this.linkUrl = linkUrl;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyAdGroupCreatedEvent(this));
    }

    public static LegacyAdGroup of(String name, Long userId, Long campaignId, String linkUrl) {
        return new LegacyAdGroup(name, userId, campaignId, linkUrl, LocalDateTime.now());
    }

    public void updateName(String newName) {
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
        registerEvent(new LegacyAdGroupNameUpdatedEvent(this));
    }

    public void updateLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        this.updatedAt = LocalDateTime.now();
        registerEvent(new LegacyAdGroupLinkUrlUpdatedEvent(this));
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        registerEvent(new LegacyAdGroupDeletedEvent(this));
    }
}
