package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.SysFunctionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysFunction} and its DTO {@link SysFunctionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SysPermissionMapper.class, SysModuleMapper.class})
public interface SysFunctionMapper extends EntityMapper<SysFunctionDTO, SysFunction> {

    @Mapping(source = "sysPermission.id", target = "sysPermissionId")
    @Mapping(source = "sysModule.id", target = "sysModuleId")
    SysFunctionDTO toDto(SysFunction sysFunction);

    @Mapping(source = "sysPermissionId", target = "sysPermission")
    @Mapping(source = "sysModuleId", target = "sysModule")
    SysFunction toEntity(SysFunctionDTO sysFunctionDTO);

    default SysFunction fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysFunction sysFunction = new SysFunction();
        sysFunction.setId(id);
        return sysFunction;
    }
}
