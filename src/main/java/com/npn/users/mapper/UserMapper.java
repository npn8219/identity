package com.npn.users.mapper;

import com.npn.users.config.MapperTemplateConfig;
import com.npn.users.model.entity.UserEntity;
import com.npn.users.model.vo.UserEntityVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperTemplateConfig.class)
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    UserEntityVo entityToVo(UserEntity entity);

}
