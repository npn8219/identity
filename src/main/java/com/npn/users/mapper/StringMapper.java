package com.npn.users.mapper;

import com.npn.users.config.MapperTemplateConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper(config = MapperTemplateConfig.class)
public interface StringMapper {

    @Named("trim")
    default String trim(String value) {
        return value.trim();
    }

    @Named("trimIfNotNull")
    default String trimIfNotNull(String value) {
        if (Objects.nonNull(value)) {
            return trim(value);
        }
        return null;
    }
}
