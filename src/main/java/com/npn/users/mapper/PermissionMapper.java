package com.npn.users.mapper;

import com.npn.users.config.MapperTemplateConfig;
import com.npn.users.model.dto.CreatePermissionDto;
import com.npn.users.model.dto.UpdatePermissionDto;
import com.npn.users.model.entity.PermissionEntity;
import com.npn.users.model.vo.PermissionEntityVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperTemplateConfig.class, uses = StringMapper.class)
public interface PermissionMapper {

    @Mapping(target = "name", qualifiedByName = "trim")
    void dtoToEntity(CreatePermissionDto dto, @MappingTarget PermissionEntity entity);

    @Mapping(target = "name", qualifiedByName = "trim")
    void dtoToEntity(UpdatePermissionDto dto, @MappingTarget PermissionEntity entity);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "roles", ignore = true)
    PermissionEntityVo entityToVo(PermissionEntity entity);
}