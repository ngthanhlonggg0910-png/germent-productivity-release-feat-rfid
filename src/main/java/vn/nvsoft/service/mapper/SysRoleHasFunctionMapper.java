package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.SysRoleHasFunctionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysRoleHasFunction} and its DTO {@link SysRoleHasFunctionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SysFunctionMapper.class, AuthorityMapper.class})
public interface SysRoleHasFunctionMapper extends EntityMapper<SysRoleHasFunctionDTO, SysRoleHasFunction> {

    @Mapping(source = "sysFunction.id", target = "sysFunctionId")
    @Mapping(source = "authority.name", target = "authorityName")
    SysRoleHasFunctionDTO toDto(SysRoleHasFunction sysRoleHasFunction);

    @Mapping(source = "sysFunctionId", target = "sysFunction")
    @Mapping(source = "authorityName", target = "authority")
    SysRoleHasFunction toEntity(SysRoleHasFunctionDTO sysRoleHasFunctionDTO);

    default SysRoleHasFunction fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRoleHasFunction sysRoleHasFunction = new SysRoleHasFunction();
        sysRoleHasFunction.setId(id);
        return sysRoleHasFunction;
    }
}
