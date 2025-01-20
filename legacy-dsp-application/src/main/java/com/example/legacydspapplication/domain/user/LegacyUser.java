package com.example.legacydspapplication.domain.user;

import com.example.legacydspapplication.domain.user.event.LegacyUserCreatedEvent;
import com.example.legacydspapplication.domain.user.event.LegacyUserDeletedEvent;
import com.example.legacydspapplication.domain.user.event.LegacyUserNameUpdatedEvent;
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
public class LegacyUser extends AbstractAggregateRoot<LegacyUser> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public LegacyUser(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyUserCreatedEvent(this));
    }

    public static LegacyUser of(String name) {
        return new LegacyUser(name, LocalDateTime.now());
    }

    public void updateName(String newName) {
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
        registerEvent(new LegacyUserNameUpdatedEvent(this));
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        registerEvent(new LegacyUserDeletedEvent(this));
    }
}
