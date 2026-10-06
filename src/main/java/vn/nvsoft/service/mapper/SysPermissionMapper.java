package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.SysPermissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysPermission} and its DTO {@link SysPermissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysPermissionMapper extends EntityMapper<SysPermissionDTO, SysPermission> {



    default SysPermission fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(id);
        return sysPermission;
    }
}
