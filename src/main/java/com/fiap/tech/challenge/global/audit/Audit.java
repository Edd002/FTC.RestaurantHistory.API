package com.fiap.tech.challenge.global.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class Audit implements Serializable {

    protected Audit() {}

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "hash_id", nullable = false, updatable = false)
    private String hashId;

    @CreatedDate
    @Column(name = "created_in", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdIn = new Date();

    @LastModifiedDate
    @Column(name = "updated_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedIn = new Date();

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
