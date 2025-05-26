package com.npn.users.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
public abstract class AbstractEntityVo implements Serializable {

    private Long id;

    private Long createdBy;

    private Long updatedBy;

    private OffsetDateTime createdOn;

    private OffsetDateTime updatedOn;

    private Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractEntityVo that)) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}