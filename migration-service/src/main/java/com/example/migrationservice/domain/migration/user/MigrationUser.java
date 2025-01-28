package com.example.migrationservice.domain.migration.user;

import com.example.migrationservice.domain.migration.user.event.MigrationAgreedEvent;
import com.example.migrationservice.domain.migration.user.event.MigrationProgressEvent;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MigrationUser extends AbstractAggregateRoot<MigrationUser> {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private MigrationUserStatus status;

    private LocalDateTime agreedAt;

    private LocalDateTime updatedAt;

    @Transient
    private MigrationUserStatus prevStatus;

    public MigrationUser(Long id, LocalDateTime agreedAt) {
        this.id = id;
        this.status = MigrationUserStatus.AGREED;
        this.agreedAt = agreedAt;
        this.updatedAt = agreedAt;
        registerEvent(new MigrationAgreedEvent(this));
    }

    public static MigrationUser agreed(Long id) {
        return new MigrationUser(id, LocalDateTime.now());
    }

    public void progressMigration() {
        this.prevStatus = this.status;
        this.status = this.status.next();
        updatedAt = LocalDateTime.now();
        registerEvent(new MigrationProgressEvent(this));
    }

    @Override
    public String toString() {
        return "MigrationUser{" +
                "id=" + id +
                ", status=" + status +
                ", agreedAt=" + agreedAt +
                ", updatedAt=" + updatedAt +
                ", prevStatus=" + prevStatus +
                '}';
    }
}
