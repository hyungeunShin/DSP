package com.example.migrationservice.domain.migration.user;

import com.example.migrationservice.domain.migration.user.event.MigrationAgreedEvent;
import com.example.migrationservice.domain.migration.user.event.MigrationProgressEvent;
import com.example.migrationservice.domain.migration.user.event.MigrationRetriedEvent;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MigrationUser extends AbstractAggregateRoot<MigrationUser> {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private MigrationUserStatus status;

    private LocalDateTime agreedAt;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
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
        if(MigrationUserStatus.RETRIED.equals(this.status)) {
            this.status = prevStatus.next();
        } else {
            this.prevStatus = this.status;
            this.status = this.status.next();
        }
        updatedAt = LocalDateTime.now();
        registerEvent(new MigrationProgressEvent(this));
    }

    public void retry() {
        if(!MigrationUserStatus.RETRIED.equals(this.status)) {
            this.prevStatus = this.status;
            this.status = MigrationUserStatus.RETRIED;
        }
        this.updatedAt = LocalDateTime.now();
        registerEvent(new MigrationRetriedEvent(this));
    }
}
