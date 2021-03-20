package com.soundlab.atividades.core;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAuditableEntity {
    @Column(name = "date_created", insertable = false, updatable = false)
    @CreationTimestamp
    protected LocalDateTime dateCreated;

    @Column(name = "date_modified", insertable = false, updatable = true)
    @UpdateTimestamp
    protected LocalDateTime dateModified;
}
