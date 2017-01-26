package com.judge_server.core.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "created", nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime updated;

    @PrePersist
    private void prePersist() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        created = now;
        updated = now;
    }

    @PreUpdate
    private void preUpdate() {
        updated = LocalDateTime.now(ZoneOffset.UTC);
    }

}
