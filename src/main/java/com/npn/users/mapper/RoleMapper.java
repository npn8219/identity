package com.npn.users.mapper;

import com.npn.users.config.MapperTemplateConfig;
import com.npn.users.model.dto.CreateRoleDto;
import com.npn.users.model.dto.UpdateRoleDto;
import com.npn.users.model.entity.RoleEntity;
import com.npn.users.model.vo.RoleEntityVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperTemplateConfig.class, uses = StringMapper.class)
public interface RoleMapper {
    @Mapping(target = "name", qualifiedByName = "trim")
    void dtoToEntity(CreateRoleDto dto, @MappingTarget RoleEntity entity);

    @Mapping(target = "name", qualifiedByName = "trim")
    void dtoToEntity(UpdateRoleDto dto, @MappingTarget RoleEntity entity);

    @Mapping(target = "permissions", ignore = true)
    RoleEntityVo entityToVo(RoleEntity entity);
}
