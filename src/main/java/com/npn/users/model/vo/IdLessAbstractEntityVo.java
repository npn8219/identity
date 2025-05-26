package com.npn.users.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
public abstract class IdLessAbstractEntityVo implements Serializable {

    private Long createdBy;

    private Long updatedBy;

    private OffsetDateTime createdOn;

    private OffsetDateTime updatedOn;

    private Boolean deleted;

}
