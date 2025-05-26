package com.npn.users.model.entity.base;

import com.npn.users.service.UserService;
import jakarta.persistence.Column;
import jakarta.persistence.Version;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.OffsetDateTime;

public abstract class IdLessAbstractEntity implements Serializable {

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

    @CreatedDate
    @Column(nullable = false)
    private OffsetDateTime createdOn;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedOn;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Version
    private Short version = 0;

}
